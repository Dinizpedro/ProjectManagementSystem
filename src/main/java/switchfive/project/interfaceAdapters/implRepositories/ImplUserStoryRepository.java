package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserStoryAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserStoryJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IUserStoryRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IUserStoryRepository;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.UserStoryCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ProductBacklog class describes the data and the methods of its objects.
 *
 * @author Maurício Pinto Barros
 * @version 0
 * @since Meeting 28-12-2021
 */

@Repository
public class ImplUserStoryRepository implements IUserStoryRepository {

    private final IUserStoryRepositoryJPA userStoryRepositoryJPA;

    private final IUserStoryAssemblerJPA userStoryAssemblerJPA;

    @Autowired
    public ImplUserStoryRepository(IUserStoryRepositoryJPA userStoryRepositoryJPA,
                                   IUserStoryAssemblerJPA userStoryAssemblerJPA) {
        this.userStoryRepositoryJPA = userStoryRepositoryJPA;
        this.userStoryAssemblerJPA = userStoryAssemblerJPA;
    }

    @Override
    public Optional<UserStory> save(UserStory newUserStory) {

        UserStoryJPA userStoryJPA = userStoryAssemblerJPA.userStoryToUserStoryJPA(newUserStory);
        UserStoryJPA userStoryJPAInDb = userStoryRepositoryJPA.save(userStoryJPA);
        UserStory userStory = userStoryAssemblerJPA.userStoryJPAtoUserStory(userStoryJPAInDb);
        return Optional.ofNullable(userStory);
    }

    /**
     * Method to calculate the default priority of an UserStory when created.
     * When created, the userStory will go to the bottom of the list of priorities.
     *
     * @return an incremental priority
     */
    @Override
    public int nextUserStoryNumber(ProjectCode projectCode) {

        List<UserStoryJPA> userStoryList = this.userStoryRepositoryJPA.findByUserStoryID_ProjectCode_OrderByPriority(projectCode.getCode());
        int nextUserStoryNumber = userStoryList.size();
        return nextUserStoryNumber + 1;
    }

    /**
     * Private method «Generate Code».
     *
     * @return an incremetal ID_UserStory that match with the created UserStory, e.g. "US1", "US2", "US3"
     */
    @Override
    public String generatorCode(ProjectCode projectCode) {
        int nextNumber = nextUserStoryNumber(projectCode);
        String code = "US" + nextNumber;
        return code;
    }

    /**
     * Get method for User Story List.
     *
     * @return a list of user stories.
     */
    @Override
    public List<UserStory> getUserStoryListProductBacklog(ProjectCode projectCode) {

        List<UserStory> userStoryList = new ArrayList<>();
        Integer sprintNumber = 0;
        List<UserStoryJPA> userStoryJPAList = this.userStoryRepositoryJPA
                .findByUserStoryID_ProjectCodeAndSprintNumberEqualsAndStatusEqualsOrderByPriority(projectCode.getCode(),
                        sprintNumber,
                        "PLANNED");

        for (UserStoryJPA userStoryJPA : userStoryJPAList) {

            UserStory userStory = this.userStoryAssemblerJPA.userStoryJPAtoUserStory(userStoryJPA);
            userStoryList.add(userStory);

        }
        return userStoryList;
    }


    /**
     * Returns the User Story in the Product Backlog given an identifying code.
     *
     * @param userStoryCode UserStoryCode obj.
     * @return UserStory object. Null if User Story is not found.
     */
    public Optional<UserStory> getUserStory(ProjectCode projectCode, UserStoryCode userStoryCode) {

        if (this.userStoryRepositoryJPA.existsByUserStoryID_UserStoryCodeAndUserStoryID_ProjectCode(userStoryCode.getIdentity(),
                projectCode.getCode())) {
            UserStoryJPA userStoryJPA = this.userStoryRepositoryJPA
                    .findByUserStoryID_UserStoryCodeAndUserStoryID_ProjectCode(userStoryCode.getIdentity(),
                            projectCode.getCode());

            UserStory userStory = this.userStoryAssemblerJPA.userStoryJPAtoUserStory(userStoryJPA);

            return Optional.of(userStory);
        }
        return Optional.empty();
    }

            @Override
    public boolean existsUserStory(ProjectCode projectCode, UserStoryCode userStoryCode) {
        String projectCodeStr = projectCode.getCode();
        String userStoryCodeStr = userStoryCode.getIdentity();

        return userStoryRepositoryJPA
                .existsByUserStoryID_UserStoryCodeAndUserStoryID_ProjectCode(userStoryCodeStr,
                        projectCodeStr);

    }

    @Override
    public List<UserStory> getUserStoriesOrderedByStatus(ProjectCode projectCode) {

        List<UserStory> userStories = new ArrayList<>();
        List<UserStoryJPA> userStoriesJPA = this.userStoryRepositoryJPA
                .findByUserStoryID_ProjectCodeOrderByStatus(projectCode.getCode());

        for (UserStoryJPA userStoryJPA : userStoriesJPA) {
            UserStory userStory = this.userStoryAssemblerJPA.userStoryJPAtoUserStory(userStoryJPA);
            userStories.add(userStory);
        }
        return userStories;
    }
}
