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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
