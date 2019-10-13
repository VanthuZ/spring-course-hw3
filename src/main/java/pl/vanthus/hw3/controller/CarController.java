package pl.vanthus.hw3.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.service.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarController {

    CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity< Resources<Car>> getCars(){

        List<Car> allCars = carService.getAllCars();
        allCars.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
        Link link = linkTo(CarController.class).withSelfRel();
        Resources<Car> carResources = new Resources<>(allCars, link);

        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Car>> getCarById(@PathVariable long id){

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()){
            Link link = linkTo(CarController.class).slash(id).withSelfRel();
            Resource<Car> carResource = new Resource<>(car.get(), link);
            return new ResponseEntity<>(carResource, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "color")
    public ResponseEntity<Resources<Car>> getCarsByColor(@RequestParam String color){

       List<Car> carList = carService.getCarsByColor(color);
       carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
       carList.forEach(car -> car.add(linkTo(CarController.class).withRel("allColors")));
       Link link = linkTo(CarController.class).withSelfRel();
       Resources<Car> carResources = new Resources<>(carList, link);

       if(carList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return new ResponseEntity<>(carResources, HttpStatus.OK);
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
