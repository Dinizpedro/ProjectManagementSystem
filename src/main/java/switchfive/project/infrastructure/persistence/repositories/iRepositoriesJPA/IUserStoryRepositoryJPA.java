package switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA;

import org.springframework.data.repository.CrudRepository;
import switchfive.project.dataModel.dataJPA.UserStoryJPA;

import java.util.List;

public interface IUserStoryRepositoryJPA extends CrudRepository<UserStoryJPA,
        String> {

    boolean existsByUserStoryID_UserStoryCodeAndUserStoryID_ProjectCode(String userStoryCode, String projectCode);

    List<UserStoryJPA> findByUserStoryID_ProjectCode_OrderByPriority(String projectCode);

    List<UserStoryJPA> findByUserStoryID_ProjectCodeAndSprintNumberEqualsAndStatusEqualsOrderByPriority(String projectCode,
                                                                                                        int sprintNumber, String status);

    UserStoryJPA findByUserStoryID_UserStoryCodeAndUserStoryID_ProjectCode(String userStoryCode, String projectCode);

    List<UserStoryJPA> findByUserStoryID_ProjectCodeOrderByStatus(String projectCode);
}
