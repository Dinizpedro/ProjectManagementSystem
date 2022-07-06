package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IProjectAssemblerJPA;
import switchfive.project.dataModel.dataJPA.ProjectJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IProjectRepositoryJPA;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Integration tests

@SpringBootTest
class ImplProjectRepositoryTest {

    @Mock
    IProjectRepositoryJPA projectRepositoryJPA;

    @Mock
    IProjectAssemblerJPA projectAssemblerJPA;

    @InjectMocks
    ImplProjectRepository projectRepository;

    @Mock
    Project projectMock;

    @Mock
    ProjectJPA projectJPAMock;

    @Test
    void findProjectByCodeSuccessfully() throws ParseException {

        String code = "Isep4";
        ProjectCode projectCodeVO = ProjectCode.create(code);
        when(projectRepositoryJPA.existsByCode(code)).thenReturn(true);

        ProjectJPA projectJPA = mock(ProjectJPA.class);
        when(projectRepositoryJPA.findByCode(code)).thenReturn(Optional.of(projectJPA));

        Project project = mock(Project.class);
        when(projectAssemblerJPA.toDomain(projectJPA)).thenReturn(project);

        Optional<Project> expected = Optional.of(project);
        Optional<Project> actual = projectRepository.findByCode(projectCodeVO);

        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnEmptyOptionalIfProjectDoesNotExistInRepository() throws ParseException {

        String code = "Isep4";
        ProjectCode projectCodeVO = ProjectCode.create(code);
        when(projectRepositoryJPA.existsByCode(code)).thenReturn(false);

        Optional<Project> expected = Optional.empty();
        Optional<Project> actual = projectRepository.findByCode(projectCodeVO);

        assertEquals(expected,actual);
    }

    @Test
    void findAllProjectsSuccessfully() throws ParseException {
        //Arrange
        List<Project> projectList = new ArrayList<>();
        projectList.add(projectMock);

        List<ProjectJPA> projectJPAList = new ArrayList<>();
        projectJPAList.add(projectJPAMock);

        when(projectRepositoryJPA.findAll()).thenReturn(projectJPAList);
        when(projectAssemblerJPA.toDomain(any(ProjectJPA.class))).thenReturn(projectMock);

        //Act
        List<Project> expected = projectRepository.findAll();

        //Assert
        assertEquals(expected,projectList);
    }

    @Test
    void findAllProjectsEmpty() throws ParseException {
        //Arrange
        List<Project> projectList = new ArrayList<>();

        List<ProjectJPA> projectJPAList = new ArrayList<>();

        when(projectRepositoryJPA.findAll()).thenReturn(projectJPAList);
        when(projectAssemblerJPA.toDomain(any(ProjectJPA.class))).thenReturn(projectMock);

        //Act
        List<Project> expected = projectRepository.findAll();

        //Assert
        assertEquals(expected,projectList);
    }
}
