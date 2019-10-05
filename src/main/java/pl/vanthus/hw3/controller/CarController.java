package pl.vanthus.hw3.controller;

import org.springframework.web.bind.annotation.*;
import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.service.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id){

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()){
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "color")
    public ResponseEntity<List<Car>> getCarsByColor(@RequestParam String color){

       List<Car> carList = carService.getCarsByColor(color);

       if(carList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return new ResponseEntity<>(carList, HttpStatus.OK);
       }
    }


}
