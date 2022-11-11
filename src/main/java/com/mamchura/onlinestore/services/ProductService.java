package com.mamchura.onlinestore.services;

import com.mamchura.onlinestore.models.Image;
import com.mamchura.onlinestore.models.Product;
import com.mamchura.onlinestore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1, image2, image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImage(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImage(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImage(image3);
        }
        product.setPreviewImageId(product.getImageList().get(0).getId());
        productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setBytes(file.getBytes());
        return image;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAllByTitle(String title) {
        return productRepository.findAllByTitle(title);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


}
