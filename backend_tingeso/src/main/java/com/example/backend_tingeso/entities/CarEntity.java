package com.example.backend_tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //va asi tal cual
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id; //no lo asigno

    private String patent; //patente de letras y numeros
    private String brand; //marca del vehiculo
    private String model; //modelo del vehiculo suzuki swift -> swift modelo
    private String type; //tipo de vehiculo, Sedan, Hatchback, SUV, Pickup, Furgoneta
    private int productionYear; //año de fabricacion
    private String motorType; //tipo de motor gasolina, diesel, hibrido y electrico
    private int numberSeats; //numero de asientos
}
