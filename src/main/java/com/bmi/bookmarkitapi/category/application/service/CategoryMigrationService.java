package com.bmi.bookmarkitapi.category.application.service;

import com.bmi.bookmarkitapi.category.domain.model.Category;
import com.bmi.bookmarkitapi.category.domain.service.CategoryCommandService;
import com.bmi.bookmarkitapi.category.domain.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryMigrationService {

    private final CategoryQueryService categoryQueryService;
    private final CategoryCommandService categoryCommandService;

    public void migrate() {
        InputStreamReader reader = new InputStreamReader(
                new BOMInputStream(getClass().getClassLoader().getResourceAsStream("category.csv")),
                StandardCharsets.UTF_8
        );
        List<Category> categories = new ArrayList<>();

        try (reader; CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader())) {
            parser.forEach(row -> {
                String mainCategory = row.get("mainCategory").trim();
                String subCategory = row.get("subCategory").trim();

                if (categoryQueryService.query(mainCategory, subCategory).isEmpty()) {
                    categories.add(new Category(mainCategory, subCategory));
                }
            });

            categoryCommandService.saveAll(categories);
            log.info("Number of saved categories: " + categories.size());
        } catch (IOException e) {
            log.error("Exception occurs when reading file " + e.getMessage());
        }
    }
}
