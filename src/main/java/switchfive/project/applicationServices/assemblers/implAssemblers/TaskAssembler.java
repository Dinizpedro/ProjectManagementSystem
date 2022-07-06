package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.springframework.hateoas.Link;
import switchfive.project.dataModel.dataJPA.TaskIDJPA;
import switchfive.project.interfaceAdapters.controllers.implControllers.ImplTaskController;
import switchfive.project.dtos.TaskDTO;
import switchfive.project.domain.aggregates.task.Task;
import switchfive.project.domain.aggregates.task.TaskContainer;
import switchfive.project.domain.shared.valueObjects.SprintID;
import switchfive.project.domain.shared.valueObjects.TaskID;
import switchfive.project.domain.shared.valueObjects.UserStoryID;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class TaskAssembler {

    /**
     * Method which transforms a Domain object - Task -
     * into a DTO object - TaskDTO.
     * @param task - domain object to be converted
     * @return a TaskDTO
     */
    public static TaskDTO toTaskDTO(Task task) {
        TaskID taskID = task.getIdTask();
        TaskContainer taskContainerID = taskID.getTaskContainer();
        String taskName = task.getName();
        String taskDescription = task.getDescription();
        String startDate = task.getStartDate();
        String endDate = task.getEndDate();
        double hoursSpent = task.getHoursSpent();
        Integer effortEstimate = task.getEffortEstimate();
        double percentageOfExecution = task.getPercentageOfExecution();
        List<TaskIDJPA> precedenceList = task.getPrecedenceList();
        String typeOfTask = task.getTypeOfTask();
        String responsible = task.getResponsible();
        String taskStatus = task.getTaskStatus();
        String projectCode = taskContainerID.getProjectCode();


        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskCode(taskID.getTaskCode());
        taskDTO.setNameDto(taskName);
        taskDTO.setDescriptionDto(taskDescription);
        taskDTO.setStartDateDto(startDate);
        taskDTO.setEndDateDto(endDate);
        taskDTO.setHourSpent(hoursSpent);
        taskDTO.setEffortDto(effortEstimate);
        taskDTO.setPercentageOfExecution(percentageOfExecution);

        List<Link> precedenceTaskLinks = new ArrayList<>();
        for (TaskIDJPA precedenceTaskID : precedenceList) {
            Link link = linkTo(methodOn(ImplTaskController.class)
                    .getTask(precedenceTaskID.getTaskCode(), projectCode))
                    .withSelfRel();

            precedenceTaskLinks.add(link);
        }

        taskDTO.setPrecedenceDto(precedenceTaskLinks);
        taskDTO.setTypeOfTaskDto(typeOfTask);
        taskDTO.setResponsibleResourceUUIDDto(responsible);
        taskDTO.setStatusOfTask(taskStatus);
        taskDTO.setProjectCodeDto(projectCode);

        if (taskContainerID.getClass() == SprintID.class) {
            SprintID sprintID = (SprintID) taskContainerID;
            Integer sprintNumber = sprintID.getSprintNumber();
            taskDTO.setSprintNumberDto(sprintNumber);
        } else if (taskContainerID.getClass() == UserStoryID.class) {
            UserStoryID userStoryID = (UserStoryID) taskContainerID;
            String userStoryCode = userStoryID.getUserStoryCode();
            taskDTO.setUserStoryCode(userStoryCode);
        }

        return taskDTO;
    }
}
