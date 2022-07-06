package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.ResourceJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IResourceRepositoryJPA extends CrudRepository<ResourceJPA, String> {

    ArrayList<ResourceJPA> getResourceJPAByProjectCode(String projectCode);

    Optional<ResourceJPA> getResourceJPAByProjectCodeAndRole(String projectCode, String role);

    List<ResourceJPA> getResourcesJPAByEmail(String email);
}
