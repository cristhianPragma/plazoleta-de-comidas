package com.pragma.plazoletas.domain.api;


import com.pragma.plazoletas.domain.model.Category;

public interface ICategoryServicePort {
    Category findById(Integer id);
}
