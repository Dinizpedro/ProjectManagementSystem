package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.domain.aggregates.userStory.UserStory;
import switchfive.project.domain.shared.valueObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//Integration tests

@SpringBootTest
class ImplUserStoryRepositoryTest {

    @Autowired
    ImplUserStoryRepository implUserStoryRepository;

    @Test
    void findDefaultPriorityWithoutPreviousSavedUS() {

        //Act
        int nextPriority = implUserStoryRepository.nextUserStoryNumber(ProjectCode.create("P0709"));
        //Assert
        assertEquals(1, nextPriority);
    }

    @Test
    void generatorCodeWithPreviousSavedUS() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0506", "US1"),
                ProjectCode.create("P0506"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0506", "US2"),
                ProjectCode.create("P0506"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryB);

        UserStory userStoryC = new UserStory(
                UserStoryID.createUserStoryID("P0202", "US1"),
                ProjectCode.create("P0202"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryC);

        //Act
        String nextUserStoryCode = implUserStoryRepository.generatorCode(
                ProjectCode.create("P0506"));
        String expected = "US3";

        //Assert
        assertEquals(expected, nextUserStoryCode);
    }

    @Test
    void generatorCodeWithoutPreviousSavedUS() {
        //Arrange
        UserStory userStoryC = new UserStory(
                UserStoryID.createUserStoryID("P0202", "US1"),
                ProjectCode.create("P0202"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryC);

        //Act
        String nextUserStoryCode = implUserStoryRepository.generatorCode(
                ProjectCode.create("P0303"));
        String expected = "US1";

        //Assert
        assertEquals(expected, nextUserStoryCode);
    }

    @Test
    void getUserStoryListProductBacklogSuccessfully() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0101", "US1"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0101", "US2"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryB);

        UserStory userStoryC = new UserStory(
                UserStoryID.createUserStoryID("P0101", "US3"),
                ProjectCode.create("P0101"),
                UserStoryCode.createUserStoryCode("US3"),
                Priority.createPriority(3),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryC);

        UserStory userStoryD = new UserStory(
                UserStoryID.createUserStoryID("P0202", "US1"),
                ProjectCode.create("P0202"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryD);

        //Act
        List<UserStory> userStoriesList = implUserStoryRepository.
                getUserStoryListProductBacklog(ProjectCode.create("P0101"));

        List<UserStory> expectedList = new ArrayList<>();
        expectedList.add(userStoryA);
        expectedList.add(userStoryB);
        expectedList.add(userStoryC);

        //Assert
        assertEquals(expectedList, userStoriesList);
        assertEquals(expectedList.size(), userStoriesList.size());
    }

    @Test
    void getUserStorySuccessfully() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US1"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US2"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("HIJ"));
        implUserStoryRepository.save(userStoryB);

        //Act
        Optional<UserStory> selectedUserStory = implUserStoryRepository.
                getUserStory(ProjectCode.create("P0838"),
                        UserStoryCode.createUserStoryCode("US2"));

        //Assert
        assertEquals(userStoryB, selectedUserStory.get());
        assertTrue(selectedUserStory.isPresent());
    }

    @Test
    void getUserStoryEmpty() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US1"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US2"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("HIJ"));
        implUserStoryRepository.save(userStoryB);

        //Act
        Optional<UserStory> selectedUserStory = implUserStoryRepository.
                getUserStory(ProjectCode.create("P0838"),
                        UserStoryCode.createUserStoryCode("US3"));

        //Assert
        assertFalse(selectedUserStory.isPresent());
    }

    @Test
    void existsUserStory() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0222", "US1"),
                ProjectCode.create("P0222"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0222", "US2"),
                ProjectCode.create("P0222"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("HIJ"));
        implUserStoryRepository.save(userStoryB);

        //Act
        boolean selectedUserStoryTrue = implUserStoryRepository.
                existsUserStory(ProjectCode.create("P0222"),
                        UserStoryCode.createUserStoryCode("US2"));

        boolean selectedUserStoryFalse = implUserStoryRepository.
                existsUserStory(ProjectCode.create("P0222"),
                        UserStoryCode.createUserStoryCode("US3"));

        //Assert
        assertTrue(selectedUserStoryTrue);
        assertFalse(selectedUserStoryFalse);
    }

    @Test
    void getUserStoryListInProjectOrderedByStatus() {
        //Arrange
        UserStory userStoryA = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US1"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US1"),
                Priority.createPriority(1),
                UserStoryDescription.createUserStoryDescription("ABC"));
        userStoryA.updateStatus("FINISHED");
        implUserStoryRepository.save(userStoryA);

        UserStory userStoryB = new UserStory(
                UserStoryID.createUserStoryID("P0838", "US2"),
                ProjectCode.create("P0838"),
                UserStoryCode.createUserStoryCode("US2"),
                Priority.createPriority(2),
                UserStoryDescription.createUserStoryDescription("HIJ"));
        implUserStoryRepository.save(userStoryB);

        ArrayList<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryA);
        userStoryList.add(userStoryB);

        //Act

        List<UserStory> listExpected = implUserStoryRepository.getUserStoriesOrderedByStatus(
                ProjectCode.create("P0838"));

        //Assert
        assertEquals(listExpected.get(1), userStoryB);
        assertEquals(listExpected.get(0), userStoryA);
    }
}
