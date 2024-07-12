package com.ezen.bookstore.category;

public record SubCategoryResponse(
        Long SubCategoryId,
        String name
) {
    public static SubCategoryResponse of(SubCategory subCategory) {
        return new SubCategoryResponse(subCategory.getId(), subCategory.getName());
    }
}
