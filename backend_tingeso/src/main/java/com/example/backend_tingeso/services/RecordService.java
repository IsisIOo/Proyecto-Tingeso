package com.example.backend_tingeso.services;

import com.example.backend_tingeso.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend_tingeso.entities.RecordEntity;
import com.example.backend_tingeso.repositories.RecordRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    CarRepository carRepository;

    public List<RecordEntity> getRecordRepository(){
        return (List<RecordEntity>) recordRepository.findAll();
    }

    public RecordEntity getOneRecordRespository(String patent){
        return recordRepository.findByPatentOne(patent);
    }

    public RecordEntity saveRecord(RecordEntity record){
        return recordRepository.save(record);
    }
    public List<RecordEntity> getRecordsByPatent(String patent) {
        return recordRepository.findByPatent(patent);
    }

    public boolean deleteRecord(Long id) throws Exception {
        try{
            recordRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //costo total sin descuentos!!!!!
    //desde aqui se recibe por entrada el repository de record
    public double precioSegunReparacionyMotor(RecordEntity rec) {
        double total_price = 0;
        String motor = carRepository.findByPatent(rec.getPatent()).getMotorType();
        System.out.println(motor); //funciona y entrega diesel
        String repairtype = rec.getRepairType();
        System.out.println(repairtype); //ntrega raparacines de transmision

        if (motor.toLowerCase().equals("gasolina")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 120000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeración")) {
                total_price = total_price + 130000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 350000;

            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmisión")) {
                total_price = total_price + 210000; //4
            }
            if (repairtype.toLowerCase().contains("reparación del sistema eléctrico")) {
                total_price = total_price + 150000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparación de neumáticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspensión y la dirección")) {
                total_price = total_price + 180000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefacción")) {
                total_price = total_price + 150000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 130000;
            }
            if (repairtype.toLowerCase().contains("reparación y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("diesel")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 120000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeración")) {
                total_price = total_price + 130000; //2
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 450000; //3
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmisión")) {
                total_price = total_price + 210000; //4
            }
            if (repairtype.toLowerCase().contains("reparación del sistema eléctrico")) {
                total_price = total_price + 150000; //5
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 120000; //6
            }
            if (repairtype.toLowerCase().contains("reparación de neumáticos y ruedas")) {
                total_price = total_price + 100000; //7
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspensión y la dirección")) {
                total_price = total_price + 180000; //8
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefacción")) {
                total_price = total_price + 150000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 140000; //10
            }
            if (repairtype.toLowerCase().contains("reparación y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("híbrido")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 180000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeración")) {
                total_price = total_price + 190000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 700000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmisión")) {
                total_price = total_price + 300000; //4
            }
            if (repairtype.toLowerCase().contains("reparación del sistema eléctrico")) {
                total_price = total_price + 200000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 450000;
            }
            if (repairtype.toLowerCase().contains("reparación de neumáticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspensión y la dirección")) {
                total_price = total_price + 210000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefacción")) {
                total_price = total_price + 180000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 220000;
            }
            if (repairtype.toLowerCase().contains("reparación y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }

        if (motor.toLowerCase().equals("eléctrico")) {
            if (repairtype.toLowerCase().contains("reparaciones del sistema de frenos")) {
                total_price = total_price + 220000;
            }
            if (repairtype.toLowerCase().contains("servicio del sistema de refrigeración")) {
                total_price = total_price + 230000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del motor")) {
                total_price = total_price + 800000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la transmisión")) {
                total_price = total_price + 300000; //4
            }
            if (repairtype.toLowerCase().contains("reparación del sistema eléctrico")) {
                total_price = total_price + 250000;
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de escape")) {
                total_price = total_price + 0;
            }
            if (repairtype.toLowerCase().contains("reparación de neumáticos y ruedas")) {
                total_price = total_price + 100000;
            }
            if (repairtype.toLowerCase().contains("reparaciones de la suspensión y la dirección")) {
                total_price = total_price + 250000;
            }
            if (repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefacción")) {
                total_price = total_price + 180000; //9
            }
            if (repairtype.toLowerCase().contains("reparaciones del sistema de combustible")) {
                total_price = total_price + 0;
            }
            if (repairtype.toLowerCase().contains("reparación y reemplazo del parabrisas y cristales")) {
                total_price = total_price + 80000;
            }
        }
        else{
            System.out.println(total_price +"kkakakakak");
            total_price= total_price;
        }
        return total_price;
    }


    //a este se le agrega segun hora y dia
    public double DescuentosSegunHora(RecordEntity rec, double total_price) {
        // ahora veo si aplica el descuento segun la hora de ingreso

        //agregar dia
        int hour = rec.getAdmissionHour();//hora para determinar si se le aplica descuento por hora de llegada
        String day = rec.getAdmissionDateDayName().toLowerCase();//dia para determinar si se le aplica descuento por dia de llegada
        if (9 < hour && hour < 12 ) {//agregar que se entre lunes y jueves
            if(day.toLowerCase().equals("jueves")  || day.toLowerCase().equals("lunes")) {
                double total_price_hour = total_price * 0.1;
                total_price = total_price - total_price_hour;
                System.out.println("El descuento aplicado por la hora RECORD SERIVE SIN 1 : " + total_price_hour);
            }
        }
        else {
            total_price = total_price;
        }
        System.out.println("Precio total de la reparación con descuento por hora: RECORD SERVICE FUERA IF" + total_price);
        return total_price;
    }

    //descuento segun marca, aun tengo dudas de este y correo blabla
    public double DescuentoSegunMarca(String patent, double total_price) {
        //descuento segun marca
        String brand = carRepository.findByPatent(patent).getBrand();
        if (brand.toLowerCase().equals("toyota")) {
            double total_price_brand = total_price * 0.1;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Toyota: " + total_price_brand);
        }
        if (brand.toLowerCase().equals("ford")) {
            double total_price_brand = total_price * 0.15;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Ford: " + total_price_brand);
        }
        if (brand.toLowerCase().equals("hyundai")) {
            double total_price_brand = total_price * 0.2;
            total_price = total_price - total_price_brand;
            System.out.println("El descuento aplicado por la marca Hyundai: " + total_price_brand);
        }
        if (brand.toLowerCase().equals("honda")) {
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
        double total_price_km=0;
        String type1 = carRepository.findByPatent(patent).getType();
        int km = carRepository.findByPatent(patent).getKilometers();
        if (type1.toLowerCase().equals("sedan")) {
            if (km <= 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km <= 12000) {
                total_price_km = total_price * 0.03;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km <= 25000) {
                total_price_km = total_price * 0.07;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km <= 40000) {
                total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Sedan por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (type1.toLowerCase().equals("hatchback")) {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                total_price_km = total_price * 0.03;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                total_price_km = total_price * 0.07;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Hatchback por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (type1.toLowerCase().equals("suv")) {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a SUV por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (type1.toLowerCase().equals("pickup")) {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Pickup por kilometraje sobre 40000: " + total_price_km);
            }
        }

        if (type1.toLowerCase().equals("furgoneta") ) {
            if (km < 5000) {
                total_price = total_price;
                System.out.println("No se aplicó recargo por kilometraje bajo 5000");
            }
            if (5001 < km && km < 12000) {
                total_price_km = total_price * 0.05;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 5000: " + total_price_km);
            }
            if (12001 < km && km < 25000) {
                total_price_km = total_price * 0.09;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 12000: " + total_price_km);
            }
            if (25001 < km && km < 40000) {
                total_price_km = total_price * 0.12;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 25000: " + total_price_km);
            }
            if (40000 < km) {
                total_price_km = total_price * 0.2;
                total_price = total_price + total_price_km;
                System.out.println("El recargo aplicado a Furgoneta por kilometraje sobre 40000: " + total_price_km);
            }
        }
        System.out.println("Precio total de la reparación con recargo por kilometraje kakaakaka: " + total_price +"y el valor es " + total_price_km);
        System.out.println("los kilometros son:" +km);
        System.out.println("el recargo por kilometraje es:" + total_price_km);
        return total_price;
    }


    public double recargoPorAntiguedad(String patent, double total_price) {
        //recargo por antiguedad
        int year_car = carRepository.findByPatent(patent).getProductionYear();
        String type1 = carRepository.findByPatent(patent).getType();
        if (type1.toLowerCase().equals("sedan")) {
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

        if (type1.toLowerCase().equals("hatchback") ) {
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

        if (type1.toLowerCase().equals("suv")) {
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
        if (type1.toLowerCase().equals("pickup") ) {
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

        if (type1.toLowerCase().equals("furgoneta") ) {
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

    public double recargoPorAtraso(RecordEntity rec, double total_price) {
        //fechas de retiro indicadas por el taller
        int dia_retiro_taller = rec.getDepartureDateDay();
        int mes_retiro_taller = rec.getDepartureDateMonth();

        //fecha retirada por el cliente
        int dia_retiro_cliente = rec.getClientDateDay();
        int mes_retiro_cliente = rec.getClientDateMonth();

        //si el retiro del cliente es mayor al retiro del taller es pq está atrasado
        if ((dia_retiro_cliente - dia_retiro_taller) > 0 && mes_retiro_cliente == mes_retiro_taller) {
            int retraso = dia_retiro_cliente - dia_retiro_taller;
            System.out.println("el cliente esta retrasado por " + retraso + "DIAS");
            //corresponde a la cantidad de dias de retraso por 5% de cada dia que se demoro
            double recargo_dias = retraso * 0.05 * total_price;
            total_price = total_price + recargo_dias;

        }
        if ((dia_retiro_cliente - dia_retiro_taller) > 0 && (mes_retiro_cliente - mes_retiro_taller) > 0) {
            int retraso = dia_retiro_cliente - dia_retiro_taller;
            int retraso_meses = mes_retiro_cliente - mes_retiro_taller;
            System.out.println("el cliente esta retrasado por " + retraso + "dias y " + retraso_meses + "meses");
            //voy a considerar que los meses solo tienen 30 dias

            //retraso de dias simples
            double recargo_dias = retraso * 0.05 * total_price;
            //por ejemplo, si son 3 meses de diferencia, para sacar los dias seria 3 *30 =90 dias y cada dia tiene 0.05 de recargo
            double recargo_meses = retraso_meses * 30 * 0.05 * total_price;

            double recargo_total = recargo_dias + recargo_meses;
            total_price = total_price + recargo_total;
        }

        System.out.println("el total con atraso es:" + total_price);
        return total_price;
    }

    //funcion donde debe entrar el historial con todo igual, solo cambiando eso
    public double getCostbyRepair(RecordEntity rec) {
        double total_price = precioSegunReparacionyMotor(rec);
        total_price = IVATOTAL(total_price); //le saca el iva al costo original
        total_price = DescuentosSegunHora(rec, total_price);
        //total_price = DescuentoSegunMarca(patent, total_price);
        //comentada el descuento segun marca porque espero usar essa funcion como un boton
        total_price = recargoPorAtraso(rec, total_price);
        total_price = RecargoPorKilometraje(rec.getPatent(), total_price);
        total_price = recargoPorAntiguedad(rec.getPatent(), total_price);
        return total_price;
    }

}

