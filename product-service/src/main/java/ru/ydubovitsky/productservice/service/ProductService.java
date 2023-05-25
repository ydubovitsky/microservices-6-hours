package ru.ydubovitsky.productservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.productservice.dto.ProductRequest;
import ru.ydubovitsky.productservice.model.Product;
import ru.ydubovitsky.productservice.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product {} saved", savedProduct.getId());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
