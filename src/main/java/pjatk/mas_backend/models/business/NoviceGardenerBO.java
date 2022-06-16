package pjatk.mas_backend.models.business;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class NoviceGardenerBO extends WorkerBO {

    private static int minYearsOfExperience = 3;

}
