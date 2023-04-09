package Kz.bitlab.SpringApplication.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;

    private String  fullName;
    private String description;
    private double price;
}
