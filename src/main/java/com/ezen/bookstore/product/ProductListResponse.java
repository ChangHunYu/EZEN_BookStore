package com.ezen.bookstore.product;

import java.time.LocalDate;

public record ProductListResponse(
        Long productId,
        String title,
        Long price,
        LocalDate publishDate,
        String author,
        String publisher,
        String imageUrl
) {
    public static ProductListResponse of(Product product) {
        return new ProductListResponse(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getPublishDate(),
                product.getAuthor(),
                product.getPublisher(),
                product.getImageUrl()
        );
    }
}
