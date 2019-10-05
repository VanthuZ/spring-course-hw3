package pl.vanthus.hw3.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.service.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/cars",
                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
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

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){

        if(carService.addCar(car)){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarById(@PathVariable long id){

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()){
            carService.deleteCar(car.get());
            return new ResponseEntity(car.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity modCarById(@PathVariable long id, @RequestBody Car newCar){

        Optional<Car> oldCar = carService.findById(id);

        if(oldCar.isPresent()){
            carService.modifyCar(oldCar.get(), newCar);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }


    }



}
