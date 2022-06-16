package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pjatk.mas_backend.models.enums.HealthState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class DemandingPlantBO extends PlantBO {

    @Min(1)
    @Max(10)
    @NonNull
    private Double groundPH;

    @Min(1)
    @Max(365)
    @NonNull
    private Integer hydratingDays;

    @NonNull
    @Enumerated(EnumType.STRING)
    private static HealthState healthState = HealthState.UNHEALTHY_DEMANDING;

}
