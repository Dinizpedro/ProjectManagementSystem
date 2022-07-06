package switchfive.project.applicationServices.appServices.iappServices;

import switchfive.project.dtos.TaskCreationDTO;
import switchfive.project.dtos.TaskDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IAppTaskService {
    Optional<TaskDTO> createAndSaveTask (TaskCreationDTO taskCreationDTO) throws ParseException;

    Optional<TaskDTO> getTask(String projectCode, String taskCode) throws ParseException;

    List<TaskDTO> getTasks() throws ParseException;
}
