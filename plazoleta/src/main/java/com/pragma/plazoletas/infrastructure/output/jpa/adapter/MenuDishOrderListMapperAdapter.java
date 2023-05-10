package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoletas.application.handler.IMenuDishOrderListMapper;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.MenuDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IMenuDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuDishOrderListMapperAdapter implements IMenuDishOrderListMapper {
    private final IMenuDishRepository menuDishRepository;
    private final IOrderDishMapper orderMapper;
    @Override
    public List<OrderDish> toOrderDishMapper(List<OrderDishRequestDto> orderDishRequestDtoList) {
        List<OrderDishEntity>orderDishEntityList = new ArrayList<>();
        if (orderDishRequestDtoList.isEmpty())
            throw new RequestException("Lista vacia", HttpStatus.NO_CONTENT);

        Long menuDishRestaurantId =0L;
        MenuDishEntity menuDishEntity;

        for (OrderDishRequestDto orderDto: orderDishRequestDtoList) {
            menuDishEntity = menuDishRepository.findById(orderDto.getMenuDishId())
                    .orElseThrow(()->new RequestException("Plato no encontrado", HttpStatus.NOT_FOUND));

            if (orderDishEntityList.isEmpty())
                menuDishRestaurantId = menuDishEntity.getRestaurant().getId();

            if(!menuDishRestaurantId.equals(menuDishEntity.getRestaurant().getId()))
                throw new RequestException("Los platos no pertenecen al mismo restaurante", HttpStatus.BAD_REQUEST);

            orderDishEntityList.add(new OrderDishEntity(null, menuDishEntity, null,orderDto.getAmount()));
        }

        return orderMapper.toOrderDishModel(orderDishEntityList);
    }
}
