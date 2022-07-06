package switchfive.project.applicationServices.assemblers.implAssemblers;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.MoveUserStoryDTO;
import switchfive.project.dtos.UserStoryDTO;
import switchfive.project.mappers.mappersApp.implMappers.UserStoryMapper;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryMapperTest {

    @Test
    void toUserStoryDTO() {
        //Arrange
        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"), UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1), UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 1), EffortEstimate.createEffortEstimate(null),
                UserStoryStatus.PLANNED, null);

        UserStoryDTO expected = new UserStoryDTO();
        expected.setDescription("ABC");
        expected.setCode("US1");
        expected.setParentUserStoryCode(null);
        expected.setEffort(null);
        expected.setPriority(1);
        expected.setStatus("PLANNED");

        //Act
        UserStoryDTO userStoryDTO = UserStoryMapper.toUserStoryDTO(userStory);

        //Assert
        assertEquals(expected, userStoryDTO);
    }

    @Test
    void toUserStoryDTOEmpty() {
        //Arrange
        UserStoryMapper assembler = new UserStoryMapper();

        UserStory userStory = new UserStory(UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"), UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1), UserStoryDescription.createUserStoryDescription("ABC"));

        UserStoryDTO expected = new UserStoryDTO();
        expected.setDescription("ABC");
        expected.setCode("US1");
        expected.setEffort(null);
        expected.setPriority(1);

        //Act
        UserStoryDTO userStoryDTO = assembler.toUserStoryDTO(userStory);

        //Assert
        assertEquals(expected, userStoryDTO);
    }

    @Test
    void shouldReturnTrueWhenDTOsAreTheSame() {
        UserStory validUserStory = new UserStory(
                UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 1),
                EffortEstimate.createEffortEstimate(1),
                UserStoryStatus.PLANNED,
                null);

        MoveUserStoryDTO expected = new MoveUserStoryDTO();
        expected.setSprintID(1);
        expected.setCode("US1");
        expected.setProjectCode("P0101");
        expected.setDescription("ABC");
        expected.setPriority(1);
        expected.setEffort(1);
        expected.setStatus("PLANNED");

        MoveUserStoryDTO actual = UserStoryMapper.toMoveUserStoryDTO(validUserStory);

        boolean expectedAndActualHaveTheSameValues =
                expected.getSprintID().equals(actual.getSprintID()) &&
                        expected.getCode().equals(actual.getCode()) &&
                        expected.getProjectCode().equals(actual.getProjectCode()) &&
                        expected.getDescription().equals(actual.getDescription()) &&
                        expected.getPriority().equals(actual.getPriority()) &&
                        expected.getEffort().equals(actual.getEffort()) &&
                        expected.getStatus().equals(actual.getStatus()) &&
                        expected.getLinks().equals(actual.getLinks());

        assertTrue(expectedAndActualHaveTheSameValues);
    }

    @Test
    void shouldReturnFalseWhenDTOsAreNotTheSame() {
        UserStory validUserStory = new UserStory(
                UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"),
                SprintID.createSprintID("P0101", 1),
                null,
                UserStoryStatus.PLANNED,
                null);

        MoveUserStoryDTO expected = new MoveUserStoryDTO();
        expected.setSprintID(-1);
        expected.setCode(null);
        expected.setProjectCode("");
        expected.setDescription("");
        expected.setPriority(-2);
        expected.setEffort(null);
        expected.setStatus("INVALID_STATUS");

        MoveUserStoryDTO actual = UserStoryMapper.toMoveUserStoryDTO(validUserStory);

        boolean expectedAndActualHaveTheSameValues =
                expected.getSprintID().equals(actual.getSprintID()) &&
                        expected.getCode().equals(actual.getCode()) &&
                        expected.getProjectCode().equals(actual.getProjectCode()) &&
                        expected.getDescription().equals(actual.getDescription()) &&
                        expected.getPriority().equals(actual.getPriority()) &&
                        expected.getEffort().equals(actual.getEffort()) &&
                        expected.getStatus().equals(actual.getStatus()) &&
                        expected.getLinks().equals(actual.getLinks());

        assertFalse(expectedAndActualHaveTheSameValues);
    }
}
