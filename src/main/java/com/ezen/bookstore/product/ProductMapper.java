package com.ezen.bookstore.product;

import com.ezen.bookstore.uitls.ProductJsonDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void insertProduct(ProductJsonDTO productJsonDTO);



}
