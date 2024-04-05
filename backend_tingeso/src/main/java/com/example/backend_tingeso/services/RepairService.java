package com.example.backend_tingeso.services;

import com.example.backend_tingeso.repositories.CarRepository;
import com.example.backend_tingeso.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_tingeso.repositories.RepairRepository;

@Service
public class RepairService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    RecordRepository recordRepository;
    RepairRepository repairRepository;

    //get reparaciones para saber cuanto sale la reparacion segun el tipo de reparacion y el tipo de motor

    public float getCost(String patent){
        int total_price =0;
        //partimos obteniendo el auto segun la patente
        String motor = carRepository.findByPatent(patent).getMotorType();
        String repairtype = recordRepository.findByPatentOne(patent).getRepairType();
        String[] partes = repairtype.split(",");

        if(motor.toLowerCase().equals("gasolina")){
            if(repairtype.toLowerCase().contains("reparaciones del sistema de frenos")){
                total_price=total_price+120000;
            }
            if(repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")){
                total_price=total_price+130000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del motor")){
                total_price=total_price+350000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la transmision:")){
                total_price=total_price+210000; //4
            }
            if(repairtype.toLowerCase().contains("reparacion del sistema electrico")){
                total_price=total_price+150000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de escape")){
                total_price=total_price+100000;
            }
            if(repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")){
                total_price=total_price+100000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")){
                total_price=total_price+180000;
            }
            if(repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")){
                total_price=total_price+150000; //9
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de combustible")){
                total_price=total_price+130000;
            }
            if(repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")){
                total_price=total_price+80000;
            }
            else{
                total_price=total_price;
            }

        }

        if(motor.toLowerCase().equals("diesel")){
            if(repairtype.toLowerCase().contains("reparaciones del sistema de frenos")){
                total_price=total_price+120000;
            }
            if(repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")){
                total_price=total_price+130000; //2
            }
            if(repairtype.toLowerCase().contains("reparaciones del motor")){
                total_price=total_price+450000; //3
            }
            if(repairtype.toLowerCase().contains("reparaciones de la transmision:")){
                total_price=total_price+210000; //4
            }
            if(repairtype.toLowerCase().contains("reparacion del sistema electrico")){
                total_price=total_price+150000; //5
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de escape")){
                total_price=total_price+120000; //6
            }
            if(repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")){
                total_price=total_price+100000; //7
            }
            if(repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")){
                total_price=total_price+180000; //8
            }
            if(repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")){
                total_price=total_price+150000; //9
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de combustible")){
                total_price=total_price+140000; //10
            }
            if(repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")){
                total_price=total_price+80000;
            }
            else{
                total_price=total_price;
            }
        }

        if(motor.toLowerCase().equals("hibrido")){
            if(repairtype.toLowerCase().contains("reparaciones del sistema de frenos")){
                total_price=total_price+180000;
            }
            if(repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")){
                total_price=total_price+190000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del motor")){
                total_price=total_price+700000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la transmision:")){
                total_price=total_price+300000; //4
            }
            if(repairtype.toLowerCase().contains("reparacion del sistema electrico")){
                total_price=total_price+200000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de escape")){
                total_price=total_price+450000;
            }
            if(repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")){
                total_price=total_price+100000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")){
                total_price=total_price+210000;
            }
            if(repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")){
                total_price=total_price+180000; //9
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de combustible")){
                total_price=total_price+220000;
            }
            if(repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")){
                total_price=total_price+80000;
            }
            else{
                total_price=total_price;
            }
        }

        if(motor.toLowerCase().equals("electrico")){
            if(repairtype.toLowerCase().contains("reparaciones del sistema de frenos")){
                total_price=total_price+220000;
            }
            if(repairtype.toLowerCase().contains("servicio del sistema de refrigeracion")){
                total_price=total_price+230000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del motor")){
                total_price=total_price+800000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la transmision:")){
                total_price=total_price+300000; //4
            }
            if(repairtype.toLowerCase().contains("reparacion del sistema electrico")){
                total_price=total_price+250000;
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de escape")){
                total_price=total_price+0;
            }
            if(repairtype.toLowerCase().contains("reparacion de neumaticos y ruedas")){
                total_price=total_price+100000;
            }
            if(repairtype.toLowerCase().contains("reparaciones de la suspension y la direccion")){
                total_price=total_price+250000;
            }
            if(repairtype.toLowerCase().contains("reparación del sistema de aire acondicionado y calefaccion")){
                total_price=total_price+180000; //9
            }
            if(repairtype.toLowerCase().contains("reparaciones del sistema de combustible")){
                total_price=total_price+0;
            }
            if(repairtype.toLowerCase().contains("reparacion y reemplazo del parabrisas y cristales")){
                total_price=total_price+80000;
            }
            else{
                total_price=total_price;
            }
        }

        System.out.println(motor);
        System.out.println(repairtype);
        return total_price;
    }

}
