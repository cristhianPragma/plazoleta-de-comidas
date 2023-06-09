package com.pragma.plazoletas.infrastructure.output.jpa.adapter;

import com.pragma.plazoletas.domain.model.Restaurant;
import com.pragma.plazoletas.domain.spi.IRestaurantPersistentPort;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoletas.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletas.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistentPort {

    private final IRestaurantRepository repository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        Optional<RestaurantEntity>findRestaurant = repository
                .findByIdOrNit(restaurant.getId(), restaurant.getNit());

        if (findRestaurant.isPresent())
            throw new RequestException("Este restaurante ya existe", HttpStatus.BAD_REQUEST);

        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        repository.save(restaurantEntity);
    }

    @Override
    public Restaurant findByRestaurantId(Long restaurantId) {
        return restaurantEntityMapper
                .toRestaurantModel(repository.findById(restaurantId)
                .orElseThrow(()->new RequestException("Restaurante no encontrado", HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<Restaurant> listAllRestaurantPaged(int pageSize, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<RestaurantEntity>pagesRestaurantEntity = repository.findAll(pageRequest);
        if (pagesRestaurantEntity.getContent().isEmpty())
            throw new RequestException("Lista vacia", HttpStatus.NO_CONTENT);
        return restaurantEntityMapper.toRestautantList(pagesRestaurantEntity.getContent());
    }

}
