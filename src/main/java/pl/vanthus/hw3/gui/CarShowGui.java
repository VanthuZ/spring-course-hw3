package pl.vanthus.hw3.gui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.service.CarService;

@Route("show-cars")
public class CarShowGui extends VerticalLayout {

    CarService carService;

    @Autowired
    public CarShowGui(CarService carService) {
        this.carService = carService;

        Grid<Car> gridCar = new Grid<>(Car.class);
        gridCar.setItems(carService.getAllCars());

        add(gridCar);

    }
}
