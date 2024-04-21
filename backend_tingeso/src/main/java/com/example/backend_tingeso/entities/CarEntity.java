package com.example.backend_tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
//import com.example.backend_tingeso.entities.RecordEntity;
//import com.example.backend_tingeso.services.RecordService;

@Entity //va asi tal cual
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder  ---> No estoy seguro si esto tiene que ir.
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id; //no lo asigno

    /*@OneToOne(cascade = CascadeType.ALL) //un auto tiene un historial de reparaciones
    //@Column(unique = true, nullable = false)
    @JoinColumn(name = "id", referencedColumnName = "id")
    public RecordEntity history; //es lista? watafac*/
    @NonNull
    private String patent; //patente de letras y numeros
    @NonNull
    private String brand; //marca del vehiculo
    @NonNull
    private String model; //modelo del vehiculo suzuki swift -> swift modelo
    @NonNull
    private String type; //tipo de vehiculo, Sedan, Hatchback, SUV, Pickup, Furgoneta
    @NonNull
    private int productionYear; //año de fabricacion
    @NonNull
    private String motorType; //tipo de motor gasolina, diesel, hibrido y electrico
    @NonNull
    private int numberSeats; //numero de asientos
    @NonNull
    private int kilometers; //kilometraje del vehiculo
}
