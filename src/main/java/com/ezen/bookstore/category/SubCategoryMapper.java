package com.ezen.bookstore.category;

import com.ezen.bookstore.uitls.SubCategoryJsonDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryMapper {
    void insertSubCategory(SubCategoryJsonDTO subCategoryJsonDTO);
}
