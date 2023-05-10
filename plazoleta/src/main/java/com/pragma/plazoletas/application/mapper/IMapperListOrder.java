package com.pragma.plazoletas.application.mapper;

import com.pragma.plazoletas.application.dto.response.OrderDishResponse;
import com.pragma.plazoletas.application.dto.response.OrderResponse;
import com.pragma.plazoletas.domain.model.Order;
import com.pragma.plazoletas.domain.model.OrderDish;
import com.pragma.plazoletas.domain.model.State;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMapperListOrder {
    List<OrderResponse> toOrderResponse(List<Order> orders);

    OrderResponse map(Order order);

    default String map(State state) {
        return state.getDescription();
    }
    private void setAttributes(List<OrderDish> orders, @MappingTarget List<OrderDishResponse> orderResponse) {
        for (int i =0; i< orders.size(); i++){
            orderResponse.get(i).setName(orders.get(i).getMenuDish().getName());
            orderResponse.get(i).setAmount(orders.get(i).getAmount());
        }
    }
    @AfterMapping
    default void mapperAttributes(List<Order> orders,
                                  @MappingTarget List<OrderResponse> orderResponses) {
        for (int i = 0; i < orderResponses.size(); i++) {
            setAttributes(orders.get(i).getOrderDishList(), orderResponses.get(i).getOrderDishList());
        }
    }
}
