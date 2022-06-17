package pjatk.mas_backend.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.entities.VisitEntity;
import pjatk.mas_backend.models.enums.LifeCycle;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ToString
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class GardenBO {

    @Nullable
    private Long id;

    @NonNull
    @NotBlank
    private String name;


    @NonNull
    @NotBlank
    private String address;

    @NonNull
    @NotBlank
    private String openHours;

    @ToString.Exclude
    @JsonIgnore
    private Set<VisitEntity> visitEntities;
}
