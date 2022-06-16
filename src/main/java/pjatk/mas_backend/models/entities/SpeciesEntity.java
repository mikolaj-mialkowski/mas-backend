package pjatk.mas_backend.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import pjatk.mas_backend.models.enums.LifeCycle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @NotBlank
    @Column(unique = true)
    private String latinName;

    @NonNull
    @Enumerated(EnumType.STRING)
    private LifeCycle lifeCycle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SpeciesEntity that = (SpeciesEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
