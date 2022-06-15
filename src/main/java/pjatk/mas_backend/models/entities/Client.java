package pjatk.mas_backend.models.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}
