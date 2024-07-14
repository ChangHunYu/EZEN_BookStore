package com.ezen.bookstore.uitls;

import com.ezen.bookstore.category.MainCategoryMapper;
import com.ezen.bookstore.category.SubCategoryMapper;
import com.ezen.bookstore.product.ProductMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final ObjectMapper objectMapper;
    private final MainCategoryMapper mainCategoryMapper;
    private final SubCategoryMapper subCategoryMapper;
    private final ProductMapper productMapper;

    public DataInitializer(ObjectMapper objectMapper, MainCategoryMapper mainCategoryMapper, SubCategoryMapper subCategoryMapper, ProductMapper productMapper) {
        this.objectMapper = objectMapper;
        this.mainCategoryMapper = mainCategoryMapper;
        this.subCategoryMapper = subCategoryMapper;
        this.productMapper = productMapper;

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadMainCategories();
        loadSubCategories();
        loadProducts();
    }



    @Transactional
    private void loadMainCategories() throws IOException {
        Resource resource = new ClassPathResource("data/MainCategories.json");
        List<MainCategoryJsonDTO> mainCategoryJsonDTOS = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<MainCategoryJsonDTO>>() {});

        for (MainCategoryJsonDTO dto : mainCategoryJsonDTOS) {
            mainCategoryMapper.insertMainCategory(dto);
        }
        
        System.out.println("Main categories loaded successfully.");
    }

    @Transactional
    private void loadSubCategories() throws IOException {
        Resource resource = new ClassPathResource("data/SubCategories.json");
        List<SubCategoryJsonDTO> subCategoryJsonDTOS = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<SubCategoryJsonDTO>>() {});

        for (SubCategoryJsonDTO dto : subCategoryJsonDTOS) {
            subCategoryMapper.insertSubCategory(dto);
        }

        System.out.println("Sub categories loaded successfully.");
    }


    @Transactional
    private void loadProducts() throws IOException {
        Resource resource = new ClassPathResource("data/Products.json");
        List<ProductJsonDTO> productJsonDTOS = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<ProductJsonDTO>>() {});

        for (ProductJsonDTO dto : productJsonDTOS) {
            productMapper.insertProduct(dto);
        }

        System.out.println("Products loaded successfully.");
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