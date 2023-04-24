package com.pragma.plazoletas.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "restaurante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "direccion", length = 30)
    private String address;

    @Column(name = "telefono_restaurante", length = 13)
    private String restaurantPhone;

    @Column(name = "url_logo")
    private String urlLogo;

    @Column(name = "nit", length = 30)
    private String nit;

    @Column(name = "id_propietario")
    private Long ownerId;
}
