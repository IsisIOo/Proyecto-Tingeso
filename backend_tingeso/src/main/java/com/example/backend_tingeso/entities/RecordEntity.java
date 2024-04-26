package com.example.backend_tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecordEntity {
    //clase para el historial de reparaciones
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;

    @NonNull
    private String patent; //patente de letras y numeros
    @NonNull
    private String admissionDateDayName; //nombre del dia de llegada
    @NonNull
    private int admissionDateDay; //fecha de llega al taller
    @NonNull
    private int admissionDateMonth;
    @NonNull
    private int admissionHour;   //hora de llegada
    @NonNull
    private String repairType; //tipo de reparacion/es

    @NonNull
    private int departureDateDay; //fecha de salida del vehiculo
    @NonNull
    private int departureDateMonth;
    @NonNull
    private int departureHour; //hora de salida, asumo que deberia ser igual a la de llegada

    private int clientDateDay; //fecha en la que el cliente se lleva el vehiculo
    private int clientDateMonth;
    private int clientHour; //hora en la que el cliente se lleva el vehiculo

    private double totalAmount;
}

