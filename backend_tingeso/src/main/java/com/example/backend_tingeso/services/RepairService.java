package com.example.backend_tingeso.services;

import com.example.backend_tingeso.entities.CarEntity;
import com.example.backend_tingeso.entities.RepairEntity;
import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import com.example.backend_tingeso.repositories.RepairRepository;

@Service
public class RepairService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    RecordRepository recordRepository;
    RepairRepository repairRepository;

    //get reparaciones para saber cuanto sale la reparacion segun el tipo de reparacion y el tipo de motor

    public double getCost(String patent) {
        double total_price = precioSegunReparacionyMotor(patent);
        System.out.println("Precio total de la reparación: " + total_price);
        total_price = DescuentosSegunHora(patent, total_price);
        //total_price = DescuentoSegunMarca(patent, total_price);
        //comentada el descuento segun marca porque espero usar essa funcion como un boton
        total_price = RecargoPorKilometraje(patent, total_price);
        total_price = recargoPorAntiguedad(patent, total_price);
        return total_price;
    }

    public double precioSegunReparacionyMotor(String patent) {
        double total_price = 0;
        String motor = carRepository.findByPatent(patent).getMotorType();
        String repairtype = recordRepository.findByPatentOne(patent).getRepairType();
        System.out.println("Motor type: " + motor);
        System.out.println("Repair type: " + repairtype);
        String[] partes = repairtype.split(",");

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


    public double DescuentosSegunHora(String patent, double total_price) {
        // ahora veo si aplica el descuento segun la hora de ingreso
        int hour = recordRepository.findByPatentOne(patent).getAdmissionHour();//hora para determinar si se le aplica descuento por hora de llegada
        if (9 < hour && hour < 12) {//agregar que se entre lunes y jueves
            double total_price_hour = total_price * 0.1;
            total_price = total_price - total_price_hour;
            System.out.println("El descuento aplicado por la hora: " + total_price_hour);
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

    public double IVA(double total_price){
        double iva = total_price * 0.19;
        total_price = total_price + iva;
        System.out.println("El IVA aplicado a la reparación: " + iva);
        System.out.println("Precio total de la reparación con IVA: " + total_price);
        return total_price;
    }

    public RepairEntity getRepairByPatent(String patent){
        return repairRepository.findByPatentrepair(patent);
    }




}

