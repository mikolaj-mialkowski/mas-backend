package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pjatk.mas_backend.models.entities.UndemandingPlantEntity;

import java.util.Set;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class NoviceGardenerBO extends WorkerBO {

    private static Integer minYearsOfExperience = 3;

    private Set<UndemandingPlantEntity> undemandingPlantEntitySet;
}
