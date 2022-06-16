package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.entities.SpeciesEntity;
import javax.persistence.ManyToOne;

@Data
@ToString
@SuperBuilder
@Validated
@RequiredArgsConstructor
@NoArgsConstructor
public class PlantBO {

    @Nullable
    private Long id;

    @NonNull
    private Boolean fertilizer;

    @NonNull
    @ManyToOne
    private SpeciesEntity speciesEntity;

}
