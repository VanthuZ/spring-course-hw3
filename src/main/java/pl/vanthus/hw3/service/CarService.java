package pl.vanthus.hw3.service;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.model.enums.Color;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {

    public List<Car> getCarList(){
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(1L, "Toyota", "Auris", Color.black));
        carList.add(new Car(2L, "Skoda", "Octavia", Color.red));
        carList.add(new Car(3L, "Mercedes", "Klasa S", Color.white));

        return carList;
    }
}
