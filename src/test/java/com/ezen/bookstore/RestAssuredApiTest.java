package com.ezen.bookstore;

import com.ezen.bookstore.product.PagedProductResponse;
import com.ezen.bookstore.product.ProductListResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestAssuredApiTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 메인카테고리_조회_테스트() {

        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/maincategory")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();

        // 응답 본문에서 categories 필드를 추출하여 검증
        List<String> categories = response.jsonPath().getList("name", String.class);
        assertThat(categories).isNotEmpty(); // name 필드가 비어있지 않은지 검증

        // 특정 값이 포함되어 있는지 확인 (예시)
        assertThat(categories).contains("소설", "여행", "과학");
    }

    @Test
    void 서브카테고리_메인카테고리ID_조회_테스트() {

        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/subcategory?mainCategoryId=1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();

        List<String> categories = response.jsonPath().getList("name", String.class);
        assertThat(categories).isNotEmpty();

    }

    @Test
    void 메인카테고리_ID_조회_테스트() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("maincategory/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();

        String category = response.jsonPath().getObject("name", String.class);
        assertThat(category).isEqualTo("소설");
    }

    @Test
    void 상품_ID_조회_테스트() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("product/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();

        String product = response.jsonPath().getObject("title", String.class);
        assertThat(product).isEqualTo("모순");
    }

    @Test
    void 상품_목록_조회_테스트_페이징() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("product?pageNumber=1&pageSize=10")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();


        List<String> products = response.jsonPath().getList("items");
        int totalPage = response.jsonPath().getInt("totalPage");
        int totalCount = response.jsonPath().getInt("totalCount");
        int pageNumber = response.jsonPath().getInt("pageNumber");
        int pageSize = response.jsonPath().getInt("pageSize");

        assertThat(products).isNotEmpty();
        assertThat(totalPage).isGreaterThan(1);
        assertThat(totalCount).isGreaterThan(1);
        assertThat(pageNumber).isEqualTo(1);
        assertThat(pageSize).isEqualTo(10);
    }

    @Test
    void 상품_목록_조회_테스트_서브카테고리ID로_검색() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("product?subCategoryId=1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();


        List<ProductListResponse> products = response.jsonPath().getList("items", ProductListResponse.class);

        assertThat(products).isNotEmpty();
    }

    @Test
    void 상품_목록_조회_테스트_메인카테고리ID로_검색() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("product?mainCategoryId=1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();


        List<ProductListResponse> products = response.jsonPath().getList("items", ProductListResponse.class);

        assertThat(products).isNotEmpty();
    }


    @Test
    void 상품_목록_조회_테스트_서브카테고리Name으로_검색() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("product?subCategoryName=한국소설")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();


        List<ProductListResponse> products = response.jsonPath().getList("items", ProductListResponse.class);

        assertThat(products).isNotEmpty();
    }

    @Test
    void 상품_목록_조회_테스트_메인카테고리Name으로_검색() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("product?mainCategoryName=여행")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();


        List<ProductListResponse> products = response.jsonPath().getList("items", ProductListResponse.class);

        assertThat(products).isNotEmpty();
    }
}
