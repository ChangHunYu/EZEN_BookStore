package com.ezen.bookstore.product;

import com.ezen.bookstore.uitls.ProductJsonDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertProduct(ProductJsonDTO productJsonDTO);
    List<ProductListResponse> findAll(ProductListSearchParams params); // 상품 전체 조회

    int countAllWithParams(ProductListSearchParams params);
}
