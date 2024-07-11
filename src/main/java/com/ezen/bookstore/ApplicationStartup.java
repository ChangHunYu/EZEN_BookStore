package com.ezen.bookstore;

import com.ezen.bookstore.category.MainCategory;
import com.ezen.bookstore.category.MainCategoryRepository;
import com.ezen.bookstore.category.SubCategory;
import com.ezen.bookstore.category.SubCategoryRepository;
import com.ezen.bookstore.product.Product;
import com.ezen.bookstore.product.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ApplicationStartup implements ApplicationRunner {

    private final ObjectMapper objectMapper;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;


    public ApplicationStartup(ObjectMapper objectMapper, MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository, ProductRepository productRepository) {
        this.objectMapper = objectMapper;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;

        // Custom Deserializer 등록
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadDataFromJson();
    }

    private void loadDataFromJson() {
        try {
            // Load Products.json from the classpath
            ClassPathResource resource1 = new ClassPathResource("data/MainCategories.json");
            ClassPathResource resource2 = new ClassPathResource("data/SubCategories.json");
            ClassPathResource resource3 = new ClassPathResource("data/Products.json");



            // Convert InputStream to Product objects using ObjectMapper
            List<MainCategory> mainCategories = objectMapper.readValue(resource1.getInputStream(), new TypeReference<List<MainCategory>>() {});
            List<SubCategory> subCategories = objectMapper.readValue(resource2.getInputStream(), new TypeReference<List<SubCategory>>() {});
            List<Product> products = objectMapper.readValue(resource3.getInputStream(), new com.fasterxml.jackson.core.type.TypeReference<List<Product>>() {});

            // Save products to database (example logic)
            mainCategories.forEach(mainCategoryRepository::save);
            subCategories.forEach(subCategoryRepository::save);
            products.forEach(productRepository::save);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Custom Deserializer for LocalDate
    private static class LocalDateDeserializer extends StdDeserializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        protected LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String date = jsonParser.getText();
            return LocalDate.parse(date, formatter);
        }
    }
}
