package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Category;
import com.pragma.plazoletas.domain.model.MenuDish;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IStateEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OderJpaAdapterTest {
    @Mock
    private IOrderDishEntityMapper orderEntityMapper;
    @Mock
    private IOrderEntityRepository orderEntityRepository;
    @Mock
    private IStateEntityRepository stateEntityRepository;
    @Mock
    private IRestaurantRepository restaurantRepository;
    @InjectMocks
    private OderJpaAdapter oderJpaAdapter;
    @Test
    void saveOderAdapterTest() {
        Integer stateId =1;
        Long restaurantId =1L;
        Long customerId =1L;
        StateEntity stateEntity = new StateEntity(stateId, "Pendiente");
        RestaurantEntity restaurant = new RestaurantEntity();
        List<OrderDishEntity> orderDishEntityList = List.of(new OrderDishEntity());
        List<OrderDish> orderDishList = List.of(new OrderDish(1, new MenuDish(1L, "Pasta",
                500, "Pasta","http://pasta.png",
                new Category(),1L,true), 1, 5));

        OrderEntity orderEntity = new OrderEntity(null, customerId, LocalDate.now(), stateEntity,
                null, restaurant, orderDishEntityList);

        when(stateEntityRepository.findById(stateId)).thenReturn(Optional.of(stateEntity));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(orderEntityMapper.toOrderDishEntity(orderDishList)).thenReturn(orderDishEntityList);
        when(orderEntityRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        oderJpaAdapter.saveOder(customerId,orderDishList);

        verify(stateEntityRepository, times(1)).findById(stateId);
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(orderEntityMapper, times(1)).toOrderDishEntity(orderDishList);
        verify(orderEntityRepository, times(1)).save(any(OrderEntity.class));
    }
}