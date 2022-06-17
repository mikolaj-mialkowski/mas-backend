package pjatk.mas_backend.models.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    private ArticleEntity articleEntity;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ExperiencedGardenerEntity experiencedGardenerEntity;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private DemandingPlantEntity demandingPlantEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CareEntity that = (CareEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
