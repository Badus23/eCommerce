package com.mamchura.onlinestore.controllers;

import com.mamchura.onlinestore.models.Product;
import com.mamchura.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemsController {

    private final ProductService productService;

    @Autowired
    public ItemsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @PostMapping("/")
    public String search(@RequestParam(value = "title", required = false) String title, Model model) {
        List<Product> productList = productService.findAllByTitle(title);
        if (title.equals("")) return "redirect:/";
        model.addAttribute("products", productList);
        return "products";
    }

    @PostMapping(value = "/product/create", headers = "content-type=multipart/*")
    public String createProduct(@RequestParam("file1")MultipartFile file1,
                                @RequestParam("file2")MultipartFile file2,
                                @RequestParam("file3")MultipartFile file3,
                                @ModelAttribute("product") Product product) throws IOException {
        productService.save(product, file1, file2, file3);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String findProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "nothingHere";
        }
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImageList());
        return "product";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
