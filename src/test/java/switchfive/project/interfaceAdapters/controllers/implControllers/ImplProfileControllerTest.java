package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.ProfileCreationDTO;
import switchfive.project.dtos.ProfileDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppProfileService;

import javax.net.ssl.SSLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplProfileControllerTest {

    @Mock
    IAppProfileService iAppProfileService;

    @InjectMocks
    ImplProfileController implProfileController;

    @Test
    void addNewProfileTrue() {
        // Arrange
        ProfileDTO profileDTOMock = mock(ProfileDTO.class);
        when(iAppProfileService.addNewProfile(anyString()))
                .thenReturn(Optional.of(profileDTOMock));

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(profileDTOMock, HttpStatus.CREATED);

        // Act
        ProfileCreationDTO profileCreationDTO
                = new ProfileCreationDTO();
        profileCreationDTO.description = "New Profile";
        ResponseEntity resultResponseEntity =
                implProfileController.addNewProfile(profileCreationDTO);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void addNewProfileFails() {
        // Arrange
        when(iAppProfileService.addNewProfile(anyString()))
                .thenReturn(Optional.empty());

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>("Profile already in Store",
                        HttpStatus.BAD_REQUEST);

        // Act
        ProfileCreationDTO profileCreationDTO
                = new ProfileCreationDTO();
        profileCreationDTO.description = "New Profile";

        ResponseEntity resultResponseEntity =
                implProfileController.addNewProfile(profileCreationDTO);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }


    @Test
    void getProfileTrue() throws SSLException {
        // Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        when(iAppProfileService.getProfile(any()))
                .thenReturn(Optional.of(profileDTO));

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(profileDTO, HttpStatus.OK);

        // Act
        String profileID = "Visitor";
        ResponseEntity resultResponseEntity =
                implProfileController.getProfile(profileID);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getProfileFalse() throws SSLException {
        // Arrange
        when(iAppProfileService.getProfile(any()))
                .thenReturn(Optional.empty());

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>("Profile not found in repository",
                        HttpStatus.NOT_FOUND);

        // Act
        String profileID = "Visitor";
        ResponseEntity resultResponseEntity =
                implProfileController.getProfile(profileID);

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getProfilesFalse() throws SSLException {
        // Arrange
        Set<ProfileDTO> profileDTOList = new HashSet<>();
        RepresentationModel<ProfileDTO> profileDTORepresentationModel =
                new RepresentationModel<>();

        when(iAppProfileService.getProfiles()).thenReturn(profileDTOList);

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(profileDTORepresentationModel,
                        HttpStatus.OK);

        // Act
        ResponseEntity resultResponseEntity =
                implProfileController.getProfiles();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getProfiles() throws SSLException {
        // Arrange
        Set<ProfileDTO> profileDTOList = new HashSet<>();
        ProfileDTO profileDTO = new ProfileDTO("Visitor");
        Link expectedLink = linkTo(methodOn(ImplProfileController.class)
                .getProfile(profileDTO.profileDescription)).withRel(profileDTO.profileDescription);
        profileDTO.add(expectedLink);
        profileDTOList.add(profileDTO);

        when(iAppProfileService.getProfiles())
                .thenReturn(profileDTOList);

        RepresentationModel<ProfileDTO> profileDTORepresentationModel =
                new RepresentationModel<>();
        profileDTORepresentationModel.add(expectedLink);

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(profileDTORepresentationModel,
                        HttpStatus.OK);

        // Act
        ResponseEntity resultResponseEntity =
                implProfileController.getProfiles();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

    @Test
    void getProfilesException() throws SSLException {
        // Arrange
        IllegalArgumentException e = new IllegalArgumentException();
        when(iAppProfileService.getProfiles())
                .thenThrow(e);

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity resultResponseEntity =
                implProfileController.getProfiles();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }
}
