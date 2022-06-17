package pjatk.mas_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private GardenEntity gardenEntity;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private AdministrationWorkerEntity administrationWorkerEntity;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private CareEntity careEntity;

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
