package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class NoviceGardenerBO extends WorkerBO {

    private static int minYearsOfExperience = 3;

}
