package switchfive.project.domain.domainServices.implDomainServices;

import org.junit.jupiter.api.Test;
import switchfive.project.dtos.ResourceCreationDTO;
import switchfive.project.domain.aggregates.project.Project;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImplResourceDomainServiceTest {
    private ImplResourceDomainService resourceDomainService = new ImplResourceDomainService();

    @Test
    void validateNewTeamMemberResource() throws ParseException {

        ArrayList<Resource> resourceList = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceList.add(resourceMock);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertTrue(resourceDomainService.validateNewTeamMemberResource(resourceList, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewTeamMemberResourceFailedBecauseIsAlreadyTeamMember() throws ParseException {

        ArrayList<Resource> resourceList = new ArrayList<>();

        ResourceID resourceID = ResourceID.createResourceID();
        Email userID = Email.create("benfica@campeao.pt");
        ProjectCode code = ProjectCode.create("DANI1");
        Time time = Time.create("27/05/2023", "27/05/2024");
        ResourceCostPerHour cost = ResourceCostPerHour.create(50);
        ResourcePercentageOfAllocation allocation = ResourcePercentageOfAllocation.create(50);
        Role role = Role.TEAM_MEMBER;

        Resource resourceTest = new Resource(resourceID, userID, code, time, cost, allocation, role);

        resourceList.add(resourceTest);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTO = new ResourceCreationDTO();
        resourceCreationDTO.userIdDto = userID.getUserEmail();
        resourceCreationDTO.projectCodeDto = code.getCode();
        resourceCreationDTO.startDateDto = "27/05/2023";
        resourceCreationDTO.endDateDto = "27/05/2024";
        resourceCreationDTO.costPerHourDto = cost.getCostPerHour();
        resourceCreationDTO.percentageOfAllocationDto = allocation.getAllocation();

        //Assert
        assertFalse(resourceDomainService.validateNewTeamMemberResource(resourceList, projectMock, resourceCreationDTO));
    }

    @Test
    void validateNewTeamMemberResourceFailedBecauseUserAlreadyTeamMemberOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewTeamMemberResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewTeamMemberResourceFailedBecauseUserAlreadyProductOwnerOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndPO(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewTeamMemberResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewTeamMemberResourceFailedBecauseDatesAreNotWithinProjectDates() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(false);
        when(resourceMock.compareUserAndPO(any())).thenReturn(false);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(false);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewTeamMemberResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewProductOwnerResourceSucess() throws ParseException {

        ArrayList<Resource> resourceList = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceList.add(resourceMock);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertTrue(resourceDomainService.validateNewProductOwnerResource(resourceList, projectMock, resourceCreationDTOMock));

    }
    @Test
    void validateNewProductOwnerResourceFailedBecauseUserAlreadyTeamMemberOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewProductOwnerResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewProductOwnerResourceFailedBecauseUserAlreadyProductOwnerOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndPO(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewProductOwnerResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewProductOwnerResourceFailedBecauseDatesAreNotWithinProjectDates() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(false);
        when(resourceMock.compareUserAndPO(any())).thenReturn(false);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(false);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewProductOwnerResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewScrumMasterResourceSucess() throws ParseException {

        ArrayList<Resource> resourceList = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceList.add(resourceMock);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertTrue(resourceDomainService.validateNewScrumMasterResource(resourceList, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewScrumMasterResourceFailedBecauseUserAlreadyTeamMemberOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewScrumMasterResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewScrumMasterResourceFailedBecauseUserAlreadyProductOwnerOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndPO(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewScrumMasterResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewScrumMasterResourceFailedBecauseUserAlreadyScrumMasterOnProject() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndSM(any())).thenReturn(true);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(true);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewScrumMasterResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }

    @Test
    void validateNewScrumMasterResourceFailedBecauseDatesAreNotWithinProjectDates() throws ParseException {

        ArrayList<Resource> resourceListMock = new ArrayList<>();

        Resource resourceMock = mock(Resource.class);
        resourceListMock.add(resourceMock);

        when(resourceMock.compareUserAndTeamMember(any())).thenReturn(false);
        when(resourceMock.compareUserAndPO(any())).thenReturn(false);
        when(resourceMock.compareUserAndSM(any())).thenReturn(false);

        Project projectMock = mock(Project.class);
        when(projectMock.areDatesWithinProjectDates(any(), any())).thenReturn(false);

        ResourceCreationDTO resourceCreationDTOMock = mock(ResourceCreationDTO.class);

        //Assert
        assertFalse(resourceDomainService.validateNewScrumMasterResource(resourceListMock, projectMock, resourceCreationDTOMock));

    }
}
