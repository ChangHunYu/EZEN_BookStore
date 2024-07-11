package com.ezen.bookstore.uitls;

import java.time.LocalDate;

public record ProductJsonDTO(
        Long id,
        String title,
        Long price,
        LocalDate publishDate,
        String author,
        String contentsInfo,
        Long sales,
        String publisher,
        String imageUrl,
        Long sub_category_id
) {
}
