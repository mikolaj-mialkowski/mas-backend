package pjatk.mas_backend.models.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class AdministrationWorkerBO extends WorkerBO{

}
