package pjatk.mas_backend.models.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import pjatk.mas_backend.models.entities.CareEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@SuperBuilder
@Getter
@RequiredArgsConstructor
@Setter
@ToString(callSuper = true)
public class ExperiencedGardenerBO extends WorkerBO {

    @Nullable
    private LocalDate promotionDate;

    @Min(1)
    @Max(3000)
    private Double salaryBonus;

    private Set<CareEntity> careEntity;


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
