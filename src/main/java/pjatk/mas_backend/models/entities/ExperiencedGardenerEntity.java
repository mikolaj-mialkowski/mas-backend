package pjatk.mas_backend.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.Objects;

@SuperBuilder
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
@ToString(callSuper = true)
public class ExperiencedGardenerEntity extends WorkerEntity {


    @Max(3000)
    @Getter
    @NonNull
    private Double salaryBonus;

    @Nullable
    private LocalDate promotionDate;

    public LocalDate getPromotionDate(){
        if (promotionDate != null)
            return promotionDate;

        else if (LocalDate.now().plusYears(3).isAfter(super.getEmploymentDate())) {
            promotionDate = super.getEmploymentDate().plusYears(3);
            return promotionDate;
        }

        return LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExperiencedGardenerEntity that = (ExperiencedGardenerEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
