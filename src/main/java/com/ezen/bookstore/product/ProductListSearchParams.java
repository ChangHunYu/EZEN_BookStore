package com.ezen.bookstore.product;

public record ProductListSearchParams(
        String sort,
        String mainCategoryId,
        String subCategoryId,
        String subCategoryName,
        Integer pageNumber,
        Integer pageSize) {

    // 레코드의 컴팩트 생성자
    public ProductListSearchParams {
        sort = sort == null ? "recent" : sort; //null이면 기본값 recent 세팅
        pageNumber = pageNumber == null ? 1 : pageNumber; // null이면 기본값 1 세팅
        pageSize = pageSize == null ? 10 : pageSize; // null이면 기본값 5 세팅
    }

    // Getter
    public Integer offset() {
        return (pageNumber - 1) * pageSize;
    }
}
