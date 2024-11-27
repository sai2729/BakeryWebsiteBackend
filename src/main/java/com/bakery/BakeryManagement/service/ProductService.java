package com.bakery.BakeryManagement.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bakery.BakeryManagement.model.Products;
import com.bakery.BakeryManagement.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }


    // Save a new product with an image
    public Products saveProduct(String name, Double price, String description, String status, MultipartFile image) throws IOException {
        Products product = new Products();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setStatus(status);

        // Store the image as a byte array
        if (image != null && !image.isEmpty()) {
            product.setImage(image.getBytes());
        }

        return productRepository.save(product);
    }

    // Get a product by ID
    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}