package Graveyard.entities.location;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import Graveyard.entities.common.BaseEntity;

@Data
@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Column(length = 10000)
    private String description;

    @Column
    private int population;

    @Column
    private String timezone;

    @Column
    private String mainAirportCode;

    @Column
    private double avgMealPrice;

    @Column
    private double avgHotelPrice;

    @Column
    private Boolean hasRecrationalWater; 
}
