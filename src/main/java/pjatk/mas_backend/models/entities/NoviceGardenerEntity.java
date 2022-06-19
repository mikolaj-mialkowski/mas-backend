package pjatk.mas_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@SuperBuilder
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Proxy(lazy = false)
@ToString(callSuper = true)
public class NoviceGardenerEntity extends WorkerEntity {

    private int minYearsOfExperience;

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private Set<UndemandingPlantEntity> undemandingPlantEntitySet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoviceGardenerEntity that = (NoviceGardenerEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
