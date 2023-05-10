package com.pragma.plazoletas.infrastructure.output.jpa.repository;


import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class IMenuDishCategoryRepositoryTest {
    @Autowired
    private IMenuDishCategoryRepository menuDishCategoryRepository;

    @Test
    void findByIdCategoryRepositoryTest(){
        int categoryId =1;
        CategoryMenuDishEntity category = new CategoryMenuDishEntity(categoryId,"Ensalda",
                "Verduras y hortalizas");
        menuDishCategoryRepository.save(category);

        Optional<CategoryMenuDishEntity>categoryFound = menuDishCategoryRepository.findById(categoryId);

        assertTrue(categoryFound.isPresent());
        assertEquals(category.getName(), categoryFound.get().getName());

    }

}