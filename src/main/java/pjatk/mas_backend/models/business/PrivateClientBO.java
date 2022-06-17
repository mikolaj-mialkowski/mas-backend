package pjatk.mas_backend.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.entities.VisitEntity;
import pjatk.mas_backend.models.enums.ClientType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ToString
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class PrivateClientBO {

    @Nullable
    private Long id;

    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String lastName;

    @NonNull
    @Min(0)
    private Long phoneNumber;

    @NonNull
    private Boolean accessToGreenhouse;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    private Set<VisitEntity> visitEntities;

}
