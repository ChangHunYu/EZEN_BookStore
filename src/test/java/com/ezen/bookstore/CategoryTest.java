package com.ezen.bookstore;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 메인카테고리조회() {
        RestAssured.when()
                .get("/maincategory")
                .then()
                .statusCode(200);
    }

    @Test
    void 서브카테고리조회() {
        RestAssured.when()
                .get("/subcategory")
                .then()
                .statusCode(200);
    }
}
