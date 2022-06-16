package pjatk.mas_backend.models.business;

import ch.qos.logback.core.net.server.Client;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import pjatk.mas_backend.models.entities.ClientEntity;
import pjatk.mas_backend.models.entities.GardenEntity;

import java.time.LocalDate;

@Data
@ToString
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class VisitBO {

    @Nullable
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private GardenEntity gardenEntity;

    @NonNull
    private ClientEntity clientEntity;
}
