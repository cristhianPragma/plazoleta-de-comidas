package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "numero_identificacion", length = 30)
    private String documentNumber;

    @Column(name = "apellido", length = 50)
    private String lastName;

    @Column(name = "numero_celular", length = 13)
    private String phone;

    @Column(name = "correo", length = 100)
    private String email;

    @Column(name = "clave")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RoleEntity roleEntity;
}
