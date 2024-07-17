package com.ezen.bookstore.product;

import java.time.LocalDate;

public record ProductDetailResponse(
        Long productId,
        String title,
        Long price,
        LocalDate publishDate,
        String author,
        String contentsInfo,
        String publisher,
        String imageUrl
) {
    public static ProductDetailResponse of(Product product) {
        return new ProductDetailResponse(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getPublishDate(),
                product.getAuthor(),
                product.getAuthor(),
                product.getPublisher(),
                product.getImageUrl()
        );
    }
}
