package pjatk.mas_backend.models.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

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

}
