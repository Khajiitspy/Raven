package Graveyard.data.dto.city;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CityCreateDTO {
    private String name;
    private String slug;
    private MultipartFile image;
    private Long countryId;
    private String description; // HTML
    private int population;
    private String timezone;
    private String mainAirportCode;
    private double avgMealPrice;
    private double avgHotelPrice;
    private Boolean hasRecrationalWater;
}
