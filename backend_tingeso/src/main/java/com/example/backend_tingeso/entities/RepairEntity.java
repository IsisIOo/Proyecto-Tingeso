package com.example.backend_tingeso.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "Cost")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patent;// para que se relacione a un auto
    private double totalOriginal; //total de la reparacion
    private double discountPerDay; //descuento por dia de atencion
    private double discountPerbonus; //descuento por bono, estas son para 4 marcas toyot, ford, hyundai y honda
    private double delayCharge; //recargo por atraso de recogida
    private double mileageCharge; //recargo por kilometraje
    private double seniorityCharge; //cargo por antiguedad del vehiculo
    private double IVA; //impuesto al valor agregado
    private double totalAmount; //total de la reparacion

    //2 descuentos
    //3 recargos
    //2 totaL
}
