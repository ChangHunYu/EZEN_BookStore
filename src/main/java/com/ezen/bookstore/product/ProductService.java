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

    public List<ProductListResponse> findAll(ProductListSearchParams params) {
        return productMapper.findAll(params);
    }

    public PagedProductResponse findAllWithPagination(ProductListSearchParams params) {
        int totalCount = productMapper.countAllWithParams(params);;
        List<ProductListResponse> items =productMapper.findAll(params);

        int totalPage =  totalCount / params.pageSize() + 1;

        return new PagedProductResponse(
                (int) totalPage,
                totalCount,
                params.pageNumber(),
                params.pageSize(),
                items
        );
    }


}
