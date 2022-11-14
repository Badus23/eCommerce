package com.mamchura.onlinestore.services;

import com.mamchura.onlinestore.models.Image;
import com.mamchura.onlinestore.models.Product;
import com.mamchura.onlinestore.models.User;
import com.mamchura.onlinestore.repositories.ProductRepository;
import com.mamchura.onlinestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void save(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
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
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImageList().get(0).getId());
        productRepository.save(product);
//        product.setPreviewImageId(product.getImageList().get(0).getId());
//        System.out.println(product);
//        productRepository.save(product);
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

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return (User) userRepository.findByEmail(principal.getName()).orElse(null);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(User user, int id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        if (product != null) {
            if (product.getUser().getId() == user.getId()) {
                productRepository.delete(product);
            }
        }
    }
}
