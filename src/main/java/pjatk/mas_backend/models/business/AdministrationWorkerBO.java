package pjatk.mas_backend.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pjatk.mas_backend.models.entities.ArticleEntity;

import javax.persistence.OneToMany;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class AdministrationWorkerBO extends WorkerBO{

    private Set<ArticleEntity> articleEntities;

}
