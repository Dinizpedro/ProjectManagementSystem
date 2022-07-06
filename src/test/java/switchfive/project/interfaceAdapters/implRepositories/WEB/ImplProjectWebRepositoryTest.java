package switchfive.project.interfaceAdapters.implRepositories.WEB;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ImplProjectWebRepositoryTest {

/*
    @Mock
    IProjectRestRepository projectRestRepository;

    @Mock
    IRestProjectAssembler restProjectAssembler;

    @InjectMocks
    ImplProjectWebRepository projectWebRepository;



    @Test
    void getProjectCode_Succesfully() throws SSLException, ParseException {

        // Arrange
        ProjectCode code = ProjectCode.create("A0001");
        ProjectName name = mock(ProjectName.class);
        ProjectDescription description = mock(ProjectDescription.class);
        CustomerName customer = mock(CustomerName.class);

        ProjectRest restProject = new ProjectRest();
        Project project = new Project(code,name,description,customer);

        when(projectRestRepository.findProjectByCode("A0001")).thenReturn(Optional.of(restProject));
        when(restProjectAssembler.toDomain(restProject)).thenReturn(project);


        // Act and Arrage

        assertEquals(projectWebRepository.getProjectByCode(code),Optional.of(project));
    }*/

}
