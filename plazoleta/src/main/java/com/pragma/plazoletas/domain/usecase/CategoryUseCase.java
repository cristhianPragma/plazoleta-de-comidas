package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.api.ICategoryServicePort;
import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.spi.ICategoryPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistentPort categoryPersistentPort;
    @Override
    public Category findById(Integer id) {
        return categoryPersistentPort.findById(id);
    }
}
