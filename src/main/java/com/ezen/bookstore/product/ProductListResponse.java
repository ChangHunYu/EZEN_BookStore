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
}
