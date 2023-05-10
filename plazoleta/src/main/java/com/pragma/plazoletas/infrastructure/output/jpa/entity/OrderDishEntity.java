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
@Table(name = "pedido_platos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_plato")
    private MenuDishEntity menuDishEntity;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private OrderEntity orderEntity;

    @Column(name = "cantidad")
    private Integer amount;
}
