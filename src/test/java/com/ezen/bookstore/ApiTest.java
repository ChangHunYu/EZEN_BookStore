package com.ezen.bookstore;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiTest {
	
	@LocalServerPort
	int port;
	
	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	@DisplayName("MainCategory findAll 테스트")
	void test_MainCategory_findAll() {
		RestAssured.when()
		.get("/maincategory")
		.then()
		.statusCode(200);
	}
	
	@Test
	@DisplayName("SubCategory findAll 테스트")
	void test_SubCategory_findAll() {
		RestAssured.when()
		.get("/subcategory")
		.then()
		.statusCode(200);
	}
}
