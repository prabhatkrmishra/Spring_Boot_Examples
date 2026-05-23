package com.example.test.service;

import com.example.test.entity.Product;
import com.example.test.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> saveAndFetchProducts(List<Product> request) {

        for (Product data : request) {
            Product product = productRepository
                    .findByProductName(data.getProductName())
                    .orElse(new Product());

            product.setProductName(data.getProductName());
            product.setPrice(data.getPrice());
            product.setQuantity(data.getQuantity());

            productRepository.save(product);
        }

        List<Product> products = productRepository.findAll();

        return products.stream()
                .sorted(
                        Comparator
                                .comparingDouble(
                                        (Product p) -> p.getPrice() * p.getQuantity()
                                )
                                .reversed()
                                .thenComparing(
                                        Product::getProductName,
                                        Comparator.reverseOrder()
                                )
                )
                .collect(Collectors.toList());
    }
}
