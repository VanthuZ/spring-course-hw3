package pl.vanthus.hw3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw3.model.Car;
import pl.vanthus.hw3.model.enums.Color;
import pl.vanthus.hw3.service.CarService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("add-car")
public class CarAdderGui extends VerticalLayout {

    private CarService carService;
    private TextField textFieldMark;
    private TextField textFieldModel;
    private  ComboBox<String> comboBoxColor;

    @Autowired
    public CarAdderGui(CarService carService) {
        this.carService = carService;

        textFieldMark = new TextField();
        textFieldMark.setPlaceholder("Write mark here");

        textFieldModel = new TextField();
        textFieldModel.setPlaceholder("Write model here");

        comboBoxColor = new ComboBox<>("Choose color");
        comboBoxColor.setItems(
                Stream.of(Color.values())
                .map(Enum::name)
                .collect(Collectors.toList())
        );

        Button button = new Button("Add new car");

        add(textFieldMark, textFieldModel, comboBoxColor, button);

        button.addClickListener(event ->{

            if(checkTextValues() == false){
                return;
            }

            Car car = new Car(
                    carService.getNextAvailableId(),
                    textFieldMark.getValue(),
                    textFieldModel.getValue(),
                    Color.valueOf(comboBoxColor.getValue()));

            if(carService.addCar(car)){
                addCarInfo(true);
            }else{
                addCarInfo(false);
            }

            clearTextFields();
        });


    }

    private void clearTextFields(){
        textFieldMark.clear();
        textFieldModel.clear();
        comboBoxColor.clear();
    }

    private void addCarInfo(boolean succes){
        Dialog dialog = new Dialog();

        if(succes){
            dialog.add(new Label("Car has been added"));
        }else{
            dialog.add(new Label("Something gone wrong"));
        }

        dialog.open();
    }

    private boolean checkTextValues(){

        if(textFieldMark.getValue() == "" || textFieldModel.getValue() == "" || comboBoxColor.getValue() == ""){
            Dialog dialog = new Dialog();
            dialog.add(new Label("Fields can't be empty"));
            dialog.open();
            return false;
        }else{
            return true;
        }
    }
}
