package com.pragma.plazoletas.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "platos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "precio")
    private int price;

    @Column(name = "descripcion", length = 100)
    private String description;

    @Column(name = "url_imagen")
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoryMenuDishEntity category;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurant;

    @Column(name = "activo")
    private boolean active;
}
