package pjatk.mas_backend.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pjatk.mas_backend.models.enums.LifeCycle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private Boolean fertilizer;

    @NonNull
    @ManyToOne
    private SpeciesEntity speciesEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlantEntity that = (PlantEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
