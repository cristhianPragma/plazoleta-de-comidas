package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.spi.ICategoryPersistentPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistentPort categoryPersistentPort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void findByIdCategoryUseCaseTest() {
        int categoryId = 1;
        Category category = new Category(categoryId,"Ensaladas", "Ensaladas");
        when(categoryPersistentPort.findById(categoryId)).thenReturn(category);

        Category categoryFound = categoryUseCase.findById(categoryId);

        assertEquals(category.getId(), categoryFound.getId());
        assertEquals(category.getName(), categoryFound.getName());
    }
}