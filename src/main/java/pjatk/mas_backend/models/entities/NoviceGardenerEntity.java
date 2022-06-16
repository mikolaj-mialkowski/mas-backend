package pjatk.mas_backend.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import java.util.Objects;

@SuperBuilder
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Proxy(lazy = false)
@ToString(callSuper = true)
public class NoviceGardenerEntity extends WorkerEntity {

    private int minYearsOfExperience;

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
