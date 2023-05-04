package com.pragma.plazoletas.infrastructure.output.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categoria_platos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMenuDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "descripcion", length = 100)
    private String description;

    @OneToMany(mappedBy ="category", fetch = FetchType.EAGER)
    private List<MenuDishEntity> menuDishEntities;

}
