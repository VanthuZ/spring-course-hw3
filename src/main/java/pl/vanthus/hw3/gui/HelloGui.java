package pl.vanthus.hw3.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route("hello")
public class HelloGui extends VerticalLayout {

    public HelloGui() {

        TextField placeholderField = new TextField();
        placeholderField.setPlaceholder("Write your name here");
        add(placeholderField);

        Button button = new Button("Hello!");
        add(button);

        Dialog dialog = new Dialog();
        button.addClickListener(event ->{
           dialog.removeAll();
           dialog.add(new Label("Hello " + placeholderField.getValue()));
           dialog.open();
        });
    }
}
