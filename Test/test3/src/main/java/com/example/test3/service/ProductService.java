package com.example.test3.service;

import org.springframework.stereotype.Service;
import com.example.test3.repository.ProductRepository;
import com.example.test3.model.Product;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            productRepository.save(existingProduct);
        }
    }


    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
