package pjatk.mas_backend.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;
import pjatk.mas_backend.models.enums.HealthState;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@SuperBuilder
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
@ToString(callSuper = true)
public class UndemandingPlantEntity extends PlantEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    private HealthState healthState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UndemandingPlantEntity that = (UndemandingPlantEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
