package com.ezen.bookstore.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> getById(@PathVariable Long id) {
        ProductDetailResponse product = productService.findById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<ProductListResponse>> getAll(@RequestParam(defaultValue = "recent") String sort) {
        List<ProductListResponse> products = productService.findAll(sort);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
