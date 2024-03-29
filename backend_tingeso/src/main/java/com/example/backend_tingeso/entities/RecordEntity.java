package com.example.backend_tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "repairHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecordEntity {
    //clase para el historial de reparaciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Date admissionDate; //fecha de llega al taller
    private int admissionHour;   //hora de llegada

    private String repairType; //tipo de reparacion/es
    private int totalAmount; //monto total de las reparaciones

    private Date departureDate; //fecha de salida del vehiculo
    private int departureHour; //hora de salida, asumo que deberia ser igual a la de llegada

    private Date clientDate; //fecha en la que el cliente se lleva el vehiculo
    private Date clientHour; //hora en la que el cliente se lleva el vehiculo

}

