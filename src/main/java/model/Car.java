package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import model.enums.Color;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private long id;
    private String mark;
    private String model;
    private Color color;
}
