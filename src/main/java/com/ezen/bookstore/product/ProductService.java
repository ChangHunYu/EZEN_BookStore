package com.ezen.bookstore.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDetailResponse findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException("id에 해당하는 상품이 없습니다.");
        }
        return ProductDetailResponse.of(product);
    }

    public List<ProductListResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductListResponse::of).toList();
    }
}
