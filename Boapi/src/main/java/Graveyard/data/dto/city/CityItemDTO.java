package Graveyard.data.dto.city;

import lombok.Data;

@Data
public class CityItemDTO {
    private Long id;
    private String name;
    private String slug;
    private String image;
    private String description;
    private int population;
    private String timezone;
    private String mainAirportCode;
    private double avgMealPrice;
    private double avgHotelPrice;
    private Boolean hasRecrationalWater;
    private Long countryId;
    private String countryName;
}
