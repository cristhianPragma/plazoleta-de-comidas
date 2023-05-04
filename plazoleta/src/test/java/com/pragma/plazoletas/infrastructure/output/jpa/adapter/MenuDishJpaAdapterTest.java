package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.CategoryMenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IMenuDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishCategoryRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuDishJpaAdapterTest {
    @Mock
    private IMenuDishRepository menuDishRepository;
    @Mock
    private IMenuDishEntityMapper menuDishEntityMapper;
    @Mock
    private IMenuDishCategoryRepository menuDishCategoryRepository;
    @Mock
    private IRestaurantRepository repository;
    @InjectMocks
    private MenuDishJpaAdapter menuDishJpaAdapter;
    private MenuDish menuDish;
    private MenuDishEntity menuDishEntity;

    @BeforeEach
    void setUp(){
         menuDish = new MenuDish(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L, true);

         menuDishEntity = new MenuDishEntity(1L,"Ensalada fria", 30000,
                "Ensalada con multiples verduras",
                "http://Ensalada.jpg",  new CategoryMenuDishEntity(1,"Ensalda",
                "Verduras y hortalizas", null),new RestaurantEntity(1L,"restaurante 1",
                "cra 1 N 162", "1255666", "http://img.png",
                "1125555", 2L), true);
    }
    @Test
    void saveMenuDishAdapterSuccessTest() {
        when(menuDishEntityMapper.toEntity(menuDish)).thenReturn(menuDishEntity);
        when(menuDishCategoryRepository.findById(menuDish.getCategoryId()))
                .thenReturn(Optional.of(menuDishEntity.getCategory()));
        when(repository.findById(menuDish.getRestaurantId()))
                .thenReturn(Optional.of(new RestaurantEntity()));
        when(menuDishRepository.save(menuDishEntity)).thenReturn(menuDishEntity);

        menuDishJpaAdapter.saveMenuDish(menuDish);

        verify(menuDishEntityMapper, times(1)).toEntity(any(MenuDish.class));
        verify(menuDishCategoryRepository, times(1)).findById(1);
        verify(repository, times(1)).findById(1L);
        verify(menuDishRepository, times(1)).save(menuDishEntity);

    }
    @Test
    void saveMenuDishAdapterCategoryExceptionTest(){
        when(menuDishEntityMapper.toEntity(menuDish)).thenReturn(menuDishEntity);
        RequestException requestException = assertThrows(RequestException.class,
                ()-> menuDishJpaAdapter.saveMenuDish(menuDish));
        assertEquals("Categoría no encontrada", requestException.getMessage());
    }
    @Test
    void saveMenuDishAdapterRestaurantExceptionTest() {
        when(menuDishEntityMapper.toEntity(menuDish)).thenReturn(menuDishEntity);
        when(menuDishCategoryRepository.findById(menuDish.getCategoryId()))
                .thenReturn(Optional.of(menuDishEntity.getCategory()));

        RequestException requestException = assertThrows(RequestException.class,
                ()-> menuDishJpaAdapter.saveMenuDish(menuDish));
        assertEquals("Restaurante no encontrado", requestException.getMessage());


    }
    @Test
    void findByIdMenuDishAdapterSuccessTest() {
        when(menuDishEntityMapper.toMenuDishModel(menuDishEntity)).thenReturn(menuDish);
        when(menuDishRepository.findById(1L))
                .thenReturn(Optional.of(menuDishEntity));
        MenuDish menuDishSaved = menuDishJpaAdapter.findByIdMenuDish(1L);
        assertEquals(menuDish.getId(), menuDishSaved.getId());
        assertEquals(menuDish.getName(), menuDishSaved.getName());
    }
    @Test
    void findByIdMenuDishAdapterExceptionTest() {
        RequestException exception = assertThrows(RequestException.class,
                ()->menuDishJpaAdapter.findByIdMenuDish(1L));
        assertEquals("Plato no encontrado", exception.getMessage());
    }
}