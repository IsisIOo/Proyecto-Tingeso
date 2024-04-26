package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import com.example.backend_tingeso.repositories.RepairRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RepairService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    RepairRepository repairRepository;
    //getter
    public ArrayList<RepairEntity> getRepair(){
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    //get reparaciones para saber cuanto sale la reparacion segun el tipo de reparacion y el tipo de motor

    public double getCost(String patent) {
        double total_price = precioSegunReparacionyMotor(patent);
        total_price = IVATOTAL(total_price); //le saca el iva al costo original
        total_price = DescuentosSegunHora(patent, total_price);
        //total_price = DescuentoSegunMarca(patent, total_price);
        //comentada el descuento segun marca porque espero usar essa funcion como un boton
        //por atraso total_price =
        total_price = RecargoPorKilometraje(patent, total_price);
        total_price = recargoPorAntiguedad(patent, total_price);
        return total_price;
    }
    //aun no hago recargo por atrado!!!




    //hace lo mismo solo que retorna una entidad
    public RepairEntity saveCostentity(String patent) {
        double total_price = precioSegunReparacionyMotor(patent);
        total_price= IVATOTAL(total_price); //le saca el iva al costo original
        total_price = DescuentosSegunHora(patent, total_price);
        //total_price = DescuentoSegunMarca(patent, total_price);
        //comentada el descuento segun marca porque espero usar essa funcion como un boton
        total_price = RecargoPorKilometraje(patent, total_price);
        total_price = recargoPorAntiguedad(patent, total_price);


        //los set para el repair entity, aplica descuento sobre descuento
        double totalOriginal = precioSegunReparacionyMotor(patent);
        double iva2 = IVASOLO(totalOriginal);
        double ivaiva = IVATOTAL(totalOriginal);
        double deshora = DescuentosSegunHora1(patent, ivaiva);
        double kilo = RecargoPorKilometraje1(patent, deshora);
        double antiguedad = recargoPorAntiguedad1(patent, kilo);


        //nuevo repair entity que se retornara
        RepairEntity repairEntity = new RepairEntity();
        repairEntity.setPatent(patent);
        repairEntity.setTotalOriginal(totalOriginal);
        repairEntity.setIVA(iva2);
        repairEntity.setDiscountPerDay(deshora);
        repairEntity.setMileageCharge(kilo);
        repairEntity.setSeniorityCharge(antiguedad);



        //sacado arriba normalmente, los set son para obtener los descuentos aplicados
        repairEntity.setTotalAmount(total_price);

        return repairRepository.save(repairEntity);
    }




    public double precioSegunReparacionyMotor(String patent) {
        double total_price = 0;
        String motor = carRepository.findByPatent(patent).getMotorType();
        String repairtype = recordRepository.findByPatentOne(patent).getRepairType();

        if (motor.toLowerCase().equals("gasolina")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 120000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")) {
                total_price = total_price + 130000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 350000;

            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmision:")) {
                total_price = total_price + 210000; //4
            }
            if (repairtype.toLowerCase().contains("reparacion del sistema electrico")) {
                total_price = total_price + 150000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")) {
                total_price = total_price + 180000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")) {
                total_price = total_price + 150000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 130000;
            }
            if (repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("diesel")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 120000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")) {
                total_price = total_price + 130000; //2
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 450000; //3
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmision:")) {
                total_price = total_price + 210000; //4
            }
            if (repairtype.toLowerCase().contains("reparacion del sistema electrico")) {
                total_price = total_price + 150000; //5
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 120000; //6
            }
            if (repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")) {
                total_price = total_price + 100000; //7
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")) {
                total_price = total_price + 180000; //8
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")) {
                total_price = total_price + 150000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 140000; //10
            }
            if (repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("hibrido")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 180000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")) {
                total_price = total_price + 190000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 700000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmision:")) {
                total_price = total_price + 300000; //4
            }
            if (repairtype.toLowerCase().contains("reparacion del sistema electrico")) {
                total_price = total_price + 200000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 450000;
            }
            if (repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")) {
                total_price = total_price + 210000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")) {
                total_price = total_price + 180000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 220000;
            }
            if (repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("electrico")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 220000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")) {
                total_price = total_price + 230000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 800000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmision:")) {
                total_price = total_price + 300000; //4
            }
            if (repairtype.toLowerCase().contains("reparacion del sistema electrico")) {
                total_price = total_price + 250000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 0;
            }
            if (repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")) {
                total_price = total_price + 250000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")) {
                total_price = total_price + 180000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 0;
            }
            if (repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        else{
            total_price= total_price;
        }
        return total_price;
    }


    //a este se le agrega segun hora y dia
    public double DescuentosSegunHora(String patent, double total_price) {
        // ahora veo si aplica el descuento segun la hora de ingreso

        //agregar dia
        int hour = recordRepository.findByPatentOne(patent).getAdmissionHour();//hora para determinar si se le aplica descuento por hora de llegada
        String day = recordRepository.findByPatentOne(patent).getAdmissionDateDayName().toLowerCase();//dia para determinar si se le aplica descuento por dia de llegada
        if (9 < hour && hour < 12 ) {//agregar que se entre lunes y jueves
           if(day.equals("jueves")  || day.equals("lunes")) {
               double total_price_hour = total_price * 0.1;
               total_price = total_price - total_price_hour;
               System.out.println("El descuento aplicado por la hora: " + total_price_hour);
           }
        }
        else {
            total_price = total_price;
        }
        System.out.println("Precio total de la reparación con descuento por hora: " + total_price);
        return total_price;
    }


    //descuento segun marca, aun tengo dudas de este y correo blabla
    public double DescuentoSegunMarca(String patent, double total_price) {
        //descuento segun marca
        String brand = carRepository.findByPatent(patent).getBrand();
        if (brand.toLowerCase() == "toyota") {
            double total_price_brand = total_price * 0.1;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Toyota: " + total_price_brand);
        }
        if (brand.toLowerCase() == "ford") {
            double total_price_brand = total_price * 0.15;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Ford: " + total_price_brand);
        }
        if (brand.toLowerCase() == "hyundai") {
            double total_price_brand = total_price * 0.2;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Hyundai: " + total_price_brand);
        }
        if (brand.toLowerCase() == "honda") {
            double total_price_brand = total_price * 0.25;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Honda: " + total_price_brand);
        }

        else {
            total_price = total_price;
            System.out.println("No se aplicó descuento por marca");
        }
        System.out.println("Precio total de la reparación con descuento por marca: " + total_price);
        return total_price;
    }



    public double RecargoPorKilometraje(String patent, double total_price) {
        //recargo por kilometraje
        String brand1 = carRepository.findByPatent(patent).getBrand();
        int km = carRepository.findByPatent(patent).getKilometers();
        if (brand1.toLowerCase() == "sedan") {
            if (km <= 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km <= 12000) {
                double total_price_km = total_price * 0.03;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km <= 25000) {
                double total_price_km = total_price * 0.07;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km <= 40000) {
                double total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                double total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (brand1.toLowerCase() == "hatchback") {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                double total_price_km = total_price * 0.03;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                double total_price_km = total_price * 0.07;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                double total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                double total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (brand1.toLowerCase() == "suv") {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                double total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                double total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                double total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                double total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (brand1.toLowerCase() == "pickup") {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                double total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                double total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                double total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                double total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (brand1.toLowerCase() == "furgoneta") {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                double total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                double total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                double total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                double total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 40000: " + total_price_km);
            }
        }
        else{
            total_price= total_price;
            System.out.println("No se aplicó recargo por kilometraje");
        }
        System.out.println("Precio total de la reparación con recargo por kilometraje: " + total_price);
        return total_price;
    }


    public double recargoPorAntiguedad(String patent, double total_price) {
        //recargo por antiguedad
        int year_car = carRepository.findByPatent(patent).getProductionYear();
        String brand1 = carRepository.findByPatent(patent).getBrand();
        if (brand1.toLowerCase() == "sedan") {
            if ((2024 - year_car) <= 5) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                double total_price_year = total_price * 0.05;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Sedan por antiguedad entre 6 y 10 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                double total_price_year = total_price * 0.09;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Sedan por antiguedad entre 11 y 15 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 16) {
                double total_price_year = total_price * 0.15;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Sedan por antiguedad sobre 16 años: " + total_price_year);
            }
        }

        if (brand1.toLowerCase() == "hatchback") {
            if ((2024 - year_car) <= 5) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                double total_price_year = total_price * 0.05;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Hatchback por antiguedad entre 6 y 10 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                double total_price_year = total_price * 0.09;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Hatchback por antiguedad entre 11 y 15 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 16) {
                double total_price_year = total_price * 0.15;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Hatchback por antiguedad sobre 16 años: " + total_price_year);
            }
        }

        if (brand1.toLowerCase() == "suv") {
            if ((2024 - year_car) <= 5) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                double total_price_year = total_price * 0.07;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a SUV por antiguedad entre 6 y 10 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                double total_price_year = total_price * 0.11;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a SUV por antiguedad entre 11 y 15 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 16) {
                double total_price_year = total_price * 0.2;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a SUV por antiguedad sobre 16 años: " + total_price_year);
            }
        }

        if (brand1.toLowerCase() == "pickup") {
            if ((2024 - year_car) <= 5) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                double total_price_year = total_price * 0.07;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Pickup por antiguedad entre 6 y 10 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                double total_price_year = total_price * 0.11;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Pickup por antiguedad entre 11 y 15 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 16) {
                double total_price_year = total_price * 0.2;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Pickup por antiguedad sobre 16 años: " + total_price_year);
            }
        }

        if (brand1.toLowerCase() == "furgoneta") {
            if ((2024 - year_car) <= 5) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                double total_price_year = total_price * 0.07;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Furgoneta por antiguedad entre 6 y 10 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                double total_price_year = total_price * 0.11;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Furgoneta por antiguedad entre 11 y 15 años: " + total_price_year);
            }

            if ((2024 - year_car) >= 16) {
                double total_price_year = total_price * 0.2;
                total_price = total_price + total_price_year;
                System.out.println("El recargo aplicado a Furgoneta por antiguedad sobre 16 años: " + total_price_year);
            }
        }
        else {
            total_price = total_price;
            System.out.println("No se aplicó recargo por antiguedad");
        }
        System.out.println("Precio total de la reparación con recargo por antiguedad: " + total_price);
        return total_price;
    }

    //total price siendo el valor original de las reparaciones aplicadas
    public double IVATOTAL(double total_price){
        double iva = total_price * 0.19;
        total_price = total_price + iva;
        System.out.println("El IVA aplicado a la reparación: " + iva);
        System.out.println("Precio total de la reparación con IVA: " + total_price);
        return total_price;
    }


    //saca solo el iva para hacerle set
    public double IVASOLO(double total_price){
        double iva = total_price * 0.19;
        return iva;
    }

    //encontrar segun tipo de reparacion
    public RepairEntity getRepairByPatent(String patent){
        return repairRepository.findByPatentrepair(patent);
    }


    //funciones para sacar el descuento
    public double recargoPorAntiguedad1(String patent, double total_price) {
        //recargo por antiguedad
        int year_car = carRepository.findByPatent(patent).getProductionYear();
        double total_price_year = 0;
        String brand1 = carRepository.findByPatent(patent).getBrand();
        if (brand1.toLowerCase() == "sedan") {
            if ((2024 - year_car) <= 5) {
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                total_price_year = total_price * 0.05;

            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                total_price_year = total_price * 0.09;

            }

            if ((2024 - year_car) >= 16) {
                total_price_year = total_price * 0.15;

            }
        }

        if (brand1.toLowerCase() == "hatchback") {
            if ((2024 - year_car) <= 5) {
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                total_price_year = total_price * 0.05;

            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                total_price_year = total_price * 0.09;

            }

            if ((2024 - year_car) >= 16) {
                total_price_year = total_price * 0.15;

            }
        }

        if (brand1.toLowerCase() == "suv") {
            if ((2024 - year_car) <= 5) {
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                total_price_year = total_price * 0.07;

            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                total_price_year = total_price * 0.11;

            }

            if ((2024 - year_car) >= 16) {
                total_price_year = total_price * 0.2;

            }
        }

        if (brand1.toLowerCase() == "pickup") {
            if ((2024 - year_car) <= 5) {
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                total_price_year = total_price * 0.07;

            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                total_price_year = total_price * 0.11;

            }

            if ((2024 - year_car) >= 16) {
                total_price_year = total_price * 0.2;

            }
        }

        if (brand1.toLowerCase() == "furgoneta") {
            if ((2024 - year_car) <= 5) {
                System.out.println("No se aplicó recargo por antiguedad bajo 5 años");
            }

            if ((2024 - year_car) >= 6 && (2024 - year_car) <= 10) {
                total_price_year = total_price * 0.07;

            }

            if ((2024 - year_car) >= 11 && (2024 - year_car) <= 15) {
                total_price_year = total_price * 0.11;

            }

            if ((2024 - year_car) >= 16) {
                total_price_year = total_price * 0.2;
            }
        }
        else {
            System.out.println("No se aplicó recargo por antiguedad 111111");
        }
        return total_price_year;
    }




    public double RecargoPorKilometraje1(String patent, double total_price) {
        //recargo por kilometraje
        double total_price_km=0;
        String brand1 = carRepository.findByPatent(patent).getBrand();
        int km = carRepository.findByPatent(patent).getKilometers();
        if (brand1.toLowerCase() == "sedan") {
            if (km <= 5000) {
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km <= 12000) {
                 total_price_km = total_price * 0.03;

            }
            if (12001 < km && km <= 25000) {
                 total_price_km = total_price * 0.07;

            }
            if (25001 < km && km <= 40000) {
                 total_price_km = total_price * 0.12;

            }
            if (40000 < km) {
                 total_price_km = total_price * 0.2;
            }
        }

        if (brand1.toLowerCase() == "hatchback") {
            if (km < 5000) {
                System.out.println("No se aplicó recargo por kilometraje bajo 5000 11111");
            }
            if (5001 < km && km < 12000) {
                 total_price_km = total_price * 0.03;
            }
            if (12001 < km && km < 25000) {
                 total_price_km = total_price * 0.07;
            }
            if (25001 < km && km < 40000) {
                 total_price_km = total_price * 0.12;

            }
            if (40000 < km) {
                 total_price_km = total_price * 0.2;

            }
        }

        if (brand1.toLowerCase() == "suv") {
            if (km < 5000) {
                System.out.println("No se aplicó recargo por kilometraje bajo 5000 11111");
            }
            if (5001 < km && km < 12000) {
                 total_price_km = total_price * 0.05;
            }
            if (12001 < km && km < 25000) {
                 total_price_km = total_price * 0.09;
            }
            if (25001 < km && km < 40000) {
                 total_price_km = total_price * 0.12;

            }
            if (40000 < km) {
                 total_price_km = total_price * 0.2;

            }
        }

        if (brand1.toLowerCase() == "pickup") {
            if (km < 5000) {
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                 total_price_km = total_price * 0.05;

            }
            if (12001 < km && km < 25000) {
                 total_price_km = total_price * 0.09;

            }
            if (25001 < km && km < 40000) {
                 total_price_km = total_price * 0.12;

            }
            if (40000 < km) {
                 total_price_km = total_price * 0.2;

            }
        }

        if (brand1.toLowerCase() == "furgoneta") {
            if (km < 5000) {
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                 total_price_km = total_price * 0.05;
            }
            if (12001 < km && km < 25000) {
                 total_price_km = total_price * 0.09;
            }
            if (25001 < km && km < 40000) {
                 total_price_km = total_price * 0.12;
            }
            if (40000 < km) {
                 total_price_km = total_price * 0.2;
            }
        }
        else{
            System.out.println("No se aplicó recargo por kilometraje");
        }
        return total_price_km;
    }

    public double DescuentosSegunHora1(String patent, double total_price) {
        // ahora veo si aplica el descuento segun la hora de ingreso
        //agregar dia
        double total_price_hour = 0;
        int hour = recordRepository.findByPatentOne(patent).getAdmissionHour();//hora para determinar si se le aplica descuento por hora de llegada
        String day = recordRepository.findByPatentOne(patent).getAdmissionDateDayName().toLowerCase();//dia para determinar si se le aplica descuento por dia de llegada
        if (9 < hour && hour < 12 ) {//agregar que se entre lunes y jueves
            if(day.equals("jueves")  || day.equals("lunes") || day.equals("martes") || day.equals("miercoles") || day.equals("viernes")) {
                total_price_hour = total_price * 0.1;
                System.out.println("El descuento aplicado por la hora: " + total_price_hour);
            }
        }
        return total_price_hour;
    }

    public boolean deleteRepair(Long id) throws Exception {
        try{
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




    public ArrayList<RepairEntity> getRepairByPatentfinal(@PathVariable String patent){
        return (ArrayList<RepairEntity>) repairRepository.findByPatentrepairfinal(patent);
    }
}

