package pjatk.mas_backend.models.business;

import lombok.*;
import org.springframework.lang.Nullable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateClientBO {

    @Nullable
    private Long id;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private Long phoneNumber;

    @Nullable
    private Boolean accessToGreenhouse;

}
