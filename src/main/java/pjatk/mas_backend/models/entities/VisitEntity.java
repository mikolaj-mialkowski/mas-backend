package pjatk.mas_backend.models.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity clientEntity;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private GardenEntity gardenEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VisitEntity that = (VisitEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
