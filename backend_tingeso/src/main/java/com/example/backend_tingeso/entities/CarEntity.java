package com.example.backend_tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private String patent; //patente de letras y numeros
    private String brand; //marca del vehiculo
    private String model; //modelo del vehiculo suzuki swift -> swift modelo
    private String type; //tipo de vehiculo, Sedan, Hatchback, SUV, Pickup, Furgoneta
    private int productionYear; //año de fabricacion
    private String motorType; //tipo de motor gasolina, diesel, hibrido y electrico
    private int numberSeats; //numero de asientos

}
