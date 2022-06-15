package pjatk.mas_backend.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;
import pjatk.mas_backend.models.enums.ClientType;
import pjatk.mas_backend.models.enums.DiscountAmount;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    private String firstName;

    private String lastName;

    private Long phoneNumber;

    @Nullable
    private Boolean accessToGreenhouse;

    @Nullable
    private String companyName;

    @Nullable
    @Enumerated(EnumType.STRING)
    private DiscountAmount discountAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClientEntity clientEntity = (ClientEntity) o;
        return id != null && Objects.equals(id, clientEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
