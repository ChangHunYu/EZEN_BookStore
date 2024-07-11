package com.ezen.bookstore.category;

import com.ezen.bookstore.uitls.MainCategoryJsonDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainCategoryMapper {
    void insertMainCategory(MainCategoryJsonDTO mainCategoryJsonDTO);
}
