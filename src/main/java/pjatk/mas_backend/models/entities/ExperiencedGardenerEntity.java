package pjatk.mas_backend.models.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@SuperBuilder
@Setter
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





}
