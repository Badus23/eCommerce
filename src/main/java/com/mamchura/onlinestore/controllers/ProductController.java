package com.mamchura.onlinestore.controllers;

import com.mamchura.onlinestore.models.Product;
import com.mamchura.onlinestore.models.User;
import com.mamchura.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(@RequestParam(name = "searchWord", required = false) String title, Model model, Principal principal) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "products";
    }

    @GetMapping("product/{id}")
    public String productInfo(@PathVariable("id") int id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImageList());
        model.addAttribute("authorProduct", product.getUser());
        return "product-info";
    }

    @PostMapping(value = "/product/create", headers = "content-type=multipart/*")
    public String createProduct(@RequestParam("file1")MultipartFile file1,
                                @RequestParam("file2")MultipartFile file2,
                                @RequestParam("file3")MultipartFile file3,
                                @ModelAttribute("product") Product product,
                                Principal principal) throws IOException {
        productService.save(principal, product, file1, file2, file3);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
        return "redirect:/my/products";
    }

    @GetMapping("/my/products")
    public String userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }
}
