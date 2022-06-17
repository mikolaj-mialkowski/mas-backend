package pjatk.mas_backend.models.business;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.entities.AdministrationWorkerEntity;
import pjatk.mas_backend.models.entities.CareEntity;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.entities.GardenEntity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@ToString
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBO {

    @Nullable
    private Long id;

    @NonNull
    @NotBlank
    private String name;

    @Min(0)
    @NonNull
    private Double price;

    @NonNull
    @NotBlank
    private String destinationInfo;

    @NonNull
    private GardenEntity gardenEntity;

    @NonNull
    private AdministrationWorkerEntity administrationWorkerEntity;

    @NonNull
    private CareEntity careEntity;
}
