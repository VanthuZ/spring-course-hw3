package pl.vanthus.hw3.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.model.enums.Color;
import pl.vanthus.hw3.service.CarService;


import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("mod-car")
public class CarModifyGui extends VerticalLayout {

    CarService carService;
    private TextField textFieldMark;
    private TextField textFieldModel;
    private ComboBox<String> comboBoxColor;
    private Button buttonSave;

    @Autowired
    public CarModifyGui(CarService carService) {
        this.carService = carService;

        textFieldMark = new TextField();
        textFieldMark.setVisible(false);

        textFieldModel = new TextField();
        textFieldModel.setVisible(false);

        comboBoxColor = new ComboBox<>();
        comboBoxColor.setVisible(false);

        ListBox<Car> listBoxCar = new ListBox<>();
        listBoxCar.setItems(carService.getAllCars());

        Button buttonDeleteCar = new Button("Delete selected car");
        Button buttonEditCar = new Button("Edit selected car");
        buttonSave = new Button("Save changes");
        buttonSave.setVisible(false);

        buttonDeleteCar.addClickListener(event -> {
            carService.deleteCar(listBoxCar.getValue());
            UI.getCurrent().getPage().reload();
        });

        buttonEditCar.addClickListener(event -> changeCar(listBoxCar.getValue()));


        add(listBoxCar, buttonDeleteCar, buttonEditCar, buttonSave);
        add(textFieldMark, textFieldModel, comboBoxColor);
    }

    private void changeCar(Car selectedCar){

       setSelectedCarData(selectedCar);
       buttonSave.setVisible(true);

       buttonSave.addClickListener(event -> saveChanges(selectedCar));
    }

    private void setSelectedCarData(Car selectedCar){

        textFieldMark.setLabel("Change mark");
        textFieldMark.setValue(selectedCar.getMark());
        textFieldMark.setVisible(true);

        textFieldModel.setLabel("Change model");
        textFieldModel.setValue(selectedCar.getModel());
        textFieldModel.setVisible(true);

        comboBoxColor.setLabel("Change color");
        comboBoxColor.setItems(
                Stream.of(Color.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
        comboBoxColor.setValue(selectedCar.getColor().name());
        comboBoxColor.setVisible(true);
    }

    private void saveChanges(Car selectedCar){

        selectedCar.setMark(textFieldMark.getValue());
        selectedCar.setModel(textFieldModel.getValue());
        selectedCar.setColor(Color.valueOf(comboBoxColor.getValue()));
        UI.getCurrent().getPage().reload();

        Dialog dialog = new Dialog();
        dialog.add(new Label("Car has been edited"));
        dialog.open();
    }

}
