package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IUserStoryAssemblerJPA;
import switchfive.project.dataModel.dataJPA.UserStoryJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IUserStoryRepositoryJPA;
import switchfive.project.domain.aggregates.userStory.UserStory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImplUserStoryRepositoryUnitTest {

    @Mock
    IUserStoryAssemblerJPA iUserStoryAssemblerJPA;

    @Mock
    IUserStoryRepositoryJPA iUserStoryRepositoryJPA;

    @InjectMocks
    ImplUserStoryRepository implUserStoryRepository;

    @Test
    void saveUserStoryEmpty(){
        //Arrange
        UserStory userStoryMock = mock(UserStory.class);
        UserStoryJPA userStoryJPAMock = mock(UserStoryJPA.class);
        UserStoryJPA savedUserStoryJPAMock = mock(UserStoryJPA.class);

        when(iUserStoryAssemblerJPA.userStoryToUserStoryJPA(userStoryMock)).thenReturn(userStoryJPAMock);
        when(iUserStoryRepositoryJPA.save(userStoryJPAMock)).thenReturn(savedUserStoryJPAMock);
        when(iUserStoryAssemblerJPA.userStoryJPAtoUserStory(savedUserStoryJPAMock)).thenReturn(null);

        //Act
        Optional<UserStory> userStory = implUserStoryRepository.save(userStoryMock);

        //Assert
        assertTrue(userStory.isEmpty());
    }
    @Test
    void saveUserStoryPresent(){
        //Arrange
        UserStory userStoryMock = mock(UserStory.class);
        UserStoryJPA userStoryJPAMock = mock(UserStoryJPA.class);
        UserStoryJPA savedUserStoryJPAMock = mock(UserStoryJPA.class);
        UserStory savedUserStoryMock = mock(UserStory.class);

        when(iUserStoryAssemblerJPA.userStoryToUserStoryJPA(userStoryMock)).thenReturn(userStoryJPAMock);
        when(iUserStoryRepositoryJPA.save(userStoryJPAMock)).thenReturn(savedUserStoryJPAMock);
        when(iUserStoryAssemblerJPA.userStoryJPAtoUserStory(savedUserStoryJPAMock)).thenReturn(savedUserStoryMock);

        //Act
        Optional<UserStory> userStory = implUserStoryRepository.save(userStoryMock);

        //Assert
        assertTrue(userStory.isPresent());
    }
}
