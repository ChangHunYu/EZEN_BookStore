package com.ezen.bookstore.product;

import java.util.List;

public record PagedProductResponse(
        int totalPage,
        int totalCount,
        Integer pageNumber,
        Integer pageSize,
        List<ProductListResponse> items
) {
}
