package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.CustomerJPA;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepositoryJPA extends
        CrudRepository<CustomerJPA, String> {

    Optional<CustomerJPA> findByName(String name);

    boolean existsByName(String name);

    <S extends CustomerJPA> S save(S entity);

    List<CustomerJPA> findAll();
}
