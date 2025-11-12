package Graveyard.data.seed;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSeed {
    private String name;
    private String slug;
    private double price;
    private String[] imagesUrl;
    private long categoryId;
    private String description;
}
