package com.ezen.bookstore;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
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
}
