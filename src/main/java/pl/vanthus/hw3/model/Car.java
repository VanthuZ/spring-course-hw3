package pl.vanthus.hw3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.ResourceSupport;
import pl.vanthus.hw3.model.enums.Color;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends ResourceSupport {

    private long id;
    private String mark;
    private String model;
    private Color color;
}
