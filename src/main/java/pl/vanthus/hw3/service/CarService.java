package pl.vanthus.hw3.service;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.model.enums.Color;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarService {

    private List<Car> carList;

    public CarService(){
       carList = new ArrayList<>();

        carList.add(new Car(1L, "Toyota", "Auris", Color.black));
        carList.add(new Car(2L, "Skoda", "Octavia", Color.red));
        carList.add(new Car(3L, "Mercedes", "Klasa S", Color.white));

    }

    public List<Car> getAllCars(){
        return carList;
    }

    public Optional<Car> findById(long id){

        Optional<Car> car = carList.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        return car;
    }

    public List<Car> getCarsByColor(String color){

        return carList.stream()
                .filter(car -> color.equalsIgnoreCase(car.getColor().name()))
                .collect(Collectors.toList());
    }

}
