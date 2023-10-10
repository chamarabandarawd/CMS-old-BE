package com.cms.cms.servise;

import com.cms.cms.dto.Car;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CarService {

    List<Car> carList=null;

    @PostConstruct
    public void loadCarsFromDB(){
        carList= IntStream.rangeClosed(1,100)
                .mapToObj(i->Car.builder()
                        .carId(i)
                        .carNumber("car-"+i)
                        .passengerCount(4)
                        .gearSystem("Auto")
                        .fuelEfficiency(new Random().nextInt(20))
                        .duration(new Random().nextInt(5)).build()
                ).collect(Collectors.toList());


    }

    public List<Car> getAllCars(){

        return carList;
    }

    public Car getCarById(int id) {
        return carList.stream()
                .filter(car->car.getCarId()==id)
                .findAny()
                .orElseThrow(()->new RuntimeException("Product "+ id+"not found"));
    }
}
