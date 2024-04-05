package com.example.backend_tingeso.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cost")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patent;// para que se relacione a un auto
    private float total; //total de la reparacion
    private float discountPerDay; //descuentopor dia de atencion
    private float discountPerbonus; //descuento por bono, estas son para 4 marcas toyot, ford, hyundai y honda
    private float delayCharge; //recargo por atraso de recogida
    private float mileageCharge; //recargo por kilometraje
    private float seniorityCharge; //cargo por antiguedad del vehiculo
}
