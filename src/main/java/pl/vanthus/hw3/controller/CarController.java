package pl.vanthus.hw3.controller;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.service.CarService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarService carService;
    private List<Car> carList;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
        carList = carService.getCarList();
    }


    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }


}
