package pjatk.mas_backend.models.business;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@ToString
@SuperBuilder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public abstract class WorkerBO {

    @Nullable
    private Long id;

    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String lastName;

    @NonNull
    private Long pesel;

    @NonNull
    private LocalDate birthDate;

    @NonNull
    private LocalDate employmentDate;

    @NonNull
    @Min(4000)
    private Double salary;

    @NonNull
    @NotBlank
    private String contactInfo;
}
