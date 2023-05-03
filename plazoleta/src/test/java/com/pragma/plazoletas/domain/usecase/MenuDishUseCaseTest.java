package com.pragma.plazoletas.domain.usecase;

import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.spi.IMenuDishPersistentPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuDishUseCaseTest {
    @Mock
    private IMenuDishPersistentPort menuDishPersistentPort;
    @InjectMocks
    private MenuDishUseCase menuDishUseCase;
    private  MenuDish menuDish;
    @BeforeEach
    void setUp(){
        menuDish = new MenuDish(1L,"Ensalada fria", 30000, "Ensalada con multiples verduras",
                "http://Ensalada.jpg", 1,1L,true);
    }

    @Test
    void saveMenuDishUseCaseTest() {
        doNothing().when(menuDishPersistentPort).saveMenuDish(menuDish);
        menuDishUseCase.saveMenuDish(menuDish);

        assertTrue(menuDish.isActive());
        verify(menuDishPersistentPort, times(1))
                .saveMenuDish(menuDish);
    }
    @Test
    void findByIdMenuDishUseCaseTest() {
        Long menuDishId = 1L;
        when(menuDishPersistentPort.findByIdMenuDish(menuDishId)).thenReturn(menuDish);

        MenuDish menuDishFound = menuDishUseCase.findByIdMenuDish(menuDishId);

        assertEquals(menuDish.getId(), menuDishFound.getId());
        assertEquals(menuDish.getName(), menuDishFound.getName());
    }
}