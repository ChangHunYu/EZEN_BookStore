package com.ezen.bookstore.uitls;

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

import jakarta.transaction.Transactional;
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
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        loadMainCategories();
        loadSubCategories();
        loadProducts();
    }

    @Transactional
    public void loadMainCategories() {
        try {
            ClassPathResource resource = new ClassPathResource("data/MainCategories.json");
            List<MainCategory> mainCategories = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<MainCategory>>() {});
            mainCategories.forEach(mainCategoryRepository::save);
            System.out.println("Main categories loaded successfully.");

            // 저장된 MainCategory 출력
            System.out.println("Saved MainCategories:");
            mainCategoryRepository.findAll().forEach(mc -> System.out.println("ID: " + mc.getId() + ", Name: " + mc.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void loadSubCategories() {
        try {
            ClassPathResource resource = new ClassPathResource("data/SubCategories.json");
            List<SubCategoryJsonDTO> subCategoryJsonDTOs = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<SubCategoryJsonDTO>>() {});

            for (SubCategoryJsonDTO dto : subCategoryJsonDTOs) {
                MainCategory mainCategory = mainCategoryRepository.findById(dto.maincategoryId())
                        .orElseThrow(() -> new RuntimeException("MainCategory not found for id: " + dto.maincategoryId()));
                SubCategory subCategory = new SubCategory(dto.name(), mainCategory);
                subCategoryRepository.save(subCategory);
            }
            System.out.println("Sub categories loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void loadProducts() {
        try {
            ClassPathResource resource = new ClassPathResource("data/Products.json");
            List<ProductJsonDTO> ProductDTOs = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<ProductJsonDTO>>() {});

            for (ProductJsonDTO dto : ProductDTOs) {
                SubCategory subCategory = subCategoryRepository.findById(dto.sub_category_id())
                        .orElseThrow(() -> new RuntimeException("SubCategory not found for id: " + dto.sub_category_id()));
                Product product = new Product(
                        dto.title(),
                        dto.price(),
                        dto.publishDate(),
                        dto.author(),
                        dto.contentsInfo(),
                        dto.sales(),
                        dto.publisher(),
                        dto.imageUrl(),
                        subCategory);
                productRepository.save(product);
            }
            System.out.println("Products loaded successfully.");
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
