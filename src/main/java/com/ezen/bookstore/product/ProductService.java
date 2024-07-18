package com.ezen.bookstore.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDetailResponse findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException("id에 해당하는 상품이 없습니다.");
        }

//        ProductDetailResponse product1 = new ProductDetailResponse(
//                product.getId(),
//                product.getTitle(),
//                product.getPrice(),
//                product.getPublishDate(),
//                product.getAuthor(),
//                product.getContentsInfo(),
//                product.getPublisher(),
//                product.getImageUrl());
//        ProductDetailResponse.of(product);

        return ProductDetailResponse.of(product);
    }

    public List<ProductListResponse> findAll(String sort) {
        return productMapper.findAll(sort);
    }
}
