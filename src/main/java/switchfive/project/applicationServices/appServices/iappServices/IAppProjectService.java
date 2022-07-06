package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.ActivityDTO;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.UpdateProjectDTO;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAppProjectService {

    Optional<ProjectDTO> getProjectDTO(String code) throws ParseException, SSLException, NoSuchAlgorithmException;

    ProjectDTO createAndSaveProject(ProjectDTO dto) throws ParseException, NoSuchAlgorithmException;

    Optional<ProjectDTO> updateProject(String projectCode, UpdateProjectDTO dto) throws ParseException;

    List<ProjectDTO> getAllProjects() throws ParseException, SSLException;

    List<ActivityDTO> getActivitiesStatuses(String projectCode) throws ParseException;

    List<ActivityDTO> getTasksStatus(String projectCode, List<ActivityDTO> activitiesDTO) throws ParseException;

    List<ActivityDTO> getUserStoriesStatus(String projectCode, List<ActivityDTO> activitiesDTO);

}
