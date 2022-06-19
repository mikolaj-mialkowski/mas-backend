package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pjatk.mas_backend.models.entities.NoviceGardenerEntity;
import pjatk.mas_backend.models.enums.HealthState;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
public class UndemandingPlantBO extends PlantBO {

    @NonNull
    @Enumerated(EnumType.STRING)
    private static HealthState healthState = HealthState.UNHEALTHY_DEMANDING;

    @NonNull
    private NoviceGardenerEntity noviceGardenerEntity;

}
