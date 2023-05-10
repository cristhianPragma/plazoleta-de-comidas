package com.pragma.plazoletas.infrastructure.output.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private Long customerId;

    @Column(name = "fecha_solicitud")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private StateEntity stateEntity;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private RestaurantEmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurantEntity;

    @OneToMany(mappedBy ="orderEntity", cascade = {CascadeType.ALL})
    private List<OrderDishEntity> orderDishList;
}
