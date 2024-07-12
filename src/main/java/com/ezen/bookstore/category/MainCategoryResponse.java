package com.ezen.bookstore.category;

public record MainCategoryResponse(
        Long MainCategoryId,
        String name
) {
    public static MainCategoryResponse of(MainCategory mainCategory) {
        return new MainCategoryResponse(mainCategory.getId(), mainCategory.getName());
    }
}
