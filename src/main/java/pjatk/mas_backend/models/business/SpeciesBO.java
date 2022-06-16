package pjatk.mas_backend.models.business;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.enums.LifeCycle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesBO {

    @Nullable
    private Long id;

    @NonNull
    @NotBlank
    private String latinName;

    @NonNull
    @Enumerated(EnumType.STRING)
    private LifeCycle lifeCycle;
}
