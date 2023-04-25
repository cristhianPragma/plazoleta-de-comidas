package com.pragma.plazoletas.domain.spi;

import com.pragma.plazoletas.domain.model.Category;

public interface ICategoryPersistentPort {
    Category findById(Integer id);
}
