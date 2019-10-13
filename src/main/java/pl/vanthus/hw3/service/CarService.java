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

    public long getNextAvailableId(){
        return carList.size() + 1;
    }

    public List<Car> getAllCars(){
        return carList;
    }

    public Optional<Car> findById(long id){

        Optional<Car> car = carList.stream()
                .filter(c -> c.getCarId() == id)
                .findFirst();

        return car;
    }

    public List<Car> getCarsByColor(String color){

        return carList.stream()
                .filter(car -> color.equalsIgnoreCase(car.getColor().name()))
                .collect(Collectors.toList());
    }

    public boolean addCar(Car car){
        return carList.add(car);
    }

    public void deleteCar(Car car){
        carList.remove(car);
    }

    public void modifyCar(Car oldCar, Car newCar){

        if(oldCar.getCarId() != newCar.getCarId() & newCar.getCarId()!=0){
            oldCar.setCarId(newCar.getCarId());
        }

        if(!oldCar.getMark().equals(newCar.getMark()) & newCar.getMark() != null){
            oldCar.setMark(newCar.getMark());
        }

        if(!oldCar.getModel().equals(newCar.getModel()) & newCar.getModel() != null){
            oldCar.setModel(newCar.getModel());
        }

        if(oldCar.getColor() != newCar.getColor() & newCar.getColor() != null){
            oldCar.setColor(newCar.getColor());
        }
    }



}
