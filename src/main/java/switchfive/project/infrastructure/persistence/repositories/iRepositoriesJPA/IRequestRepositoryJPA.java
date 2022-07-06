package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.RequestJPA;

import java.util.Optional;

public interface IRequestRepositoryJPA extends CrudRepository<RequestJPA, String> {

    Optional<RequestJPA> findByUserIDAndProfileDescription (String userID,
                                                            String profileDescription);

}
