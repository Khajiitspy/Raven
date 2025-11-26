package Graveyard.entities.location;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import Graveyard.entities.common.BaseEntity;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class CountryEntity extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String slug;

    private String image;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityEntity> cities;
}
