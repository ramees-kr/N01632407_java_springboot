package com.example.test3.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.test3.repository.ProductRepository;
import com.example.test3.model.Product;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

}
