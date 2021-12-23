package com.bmi.bookmarkitapi.category.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CategoryMigrationServiceTest {

    @Autowired
    CategoryMigrationService categoryMigrationService;

    @Test
    void migrate() throws IOException {
        categoryMigrationService.migrate();
    }
}
