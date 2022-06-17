package pjatk.mas_backend.models.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @NotBlank
    private String name;

    @Min(0)
    @NonNull
    private Double price;

    @NonNull
    @NotBlank
    private String destinationInfo;

    @ManyToOne
    private GardenEntity gardenEntity;

    @ManyToOne
    private AdministrationWorkerEntity administrationWorkerEntity;

    //CARE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleEntity that = (ArticleEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
