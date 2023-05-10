package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.spi.IOrderPersistentPort;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.StateEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderDishMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IOrderEntityListMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IOrderEntityRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IStateEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
public class OderJpaAdapter implements IOrderPersistentPort {

    private final IOrderDishEntityMapper orderEntityMapper;
    private final IOrderEntityRepository orderEntityRepository;
    private final IStateEntityRepository stateEntityRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEmployeeRepository employeeRepository;
    private final IOrderEntityListMapper orderEntityListMapper;
    private final IOrderDishMapper orderDishMapper;
    @Override
    public void saveOder(Long customerId, List<OrderDish> orderDishList) {
        StateEntity stateEntity = stateEntityRepository.findById(1)
                .orElseThrow(()->new RequestException("Estado no encontrado", HttpStatus.NOT_FOUND));

        RestaurantEntity restaurant = restaurantRepository
                .findById(orderDishList.get(0).getMenuDish().getRestaurantId())
                .orElseThrow(()->new RequestException("Restaurante no encontrado", HttpStatus.NOT_FOUND));

        List<OrderDishEntity>orderDishEntityList = orderEntityMapper.toOrderDishEntity(orderDishList);

        OrderEntity orderEntity = new OrderEntity(null, customerId, LocalDate.now(), stateEntity,
                null, restaurant, orderDishEntityList);

        orderDishEntityList.forEach(orderDish-> orderDish.setOrderEntity(orderEntity));

        orderEntityRepository.save(orderEntity);
    }

    @Override
    public List<Order> listOrder(Integer stateId, int pageSize, int pageNumber, Long employeeId) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        StateEntity state = stateEntityRepository.findById(stateId)
                .orElseThrow(()-> new RequestException("Estado no encontrado", HttpStatus.NOT_FOUND));

        RestaurantEmployeeEntity employee = employeeRepository.findById(employeeId)
                 .orElseThrow(()-> new RequestException("Empleado no encontrado", HttpStatus.NOT_FOUND));

        RestaurantEntity restaurant = restaurantRepository.findById(employee.getRestaurantId())
                .orElseThrow(()-> new RequestException("Restaurante no encontrado", HttpStatus.NOT_FOUND));

        Page<OrderEntity> orderEntities = orderEntityRepository
                .findByStateEntityAndRestaurantEntity(state, restaurant, pageRequest);

        List<Order> orders = orderEntityListMapper.orderList(orderEntities.getContent());

        for(int i =0; i< orders.size(); i++){
            orders.get(i).setOrderDishList(orderDishMapper
                    .toOrderDishModel(orderEntities.getContent().get(i).getOrderDishList()));
        }
        return orders;
    }
}
