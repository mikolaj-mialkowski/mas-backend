package pjatk.mas_backend.models.business;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class ExperiencedGardenerBO extends WorkerBO {

    @Nullable
    private LocalDate promotionDate;

    @Max(3000)
    private Double salaryBonus;

}
