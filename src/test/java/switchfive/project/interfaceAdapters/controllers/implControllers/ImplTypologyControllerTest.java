package switchfive.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.applicationServices.appServices.implAppServices.ImplAppTypologyService;
import switchfive.project.applicationServices.assemblers.implAssemblers.TypologyAssembler;
import switchfive.project.domain.aggregates.typology.Typology;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ImplTypologyControllerTest {
    private final String WRONG_INFORMATION = "Wrong information";
    private final String TYPOLOGY_ALREADY_EXISTS = "Typology already exists";
    private final String TYPOLOGY_DOES_NOT_EXIST = "Typology doesn't exist";


    @Test
    void createTypologySuccess() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        Typology typologyMock = mock(Typology.class);

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);


        when(typologyServiceMock.findTypologyByDescription(
                anyString())).thenReturn(Optional.empty());

        when(typologyServiceMock.addNewTypology(anyString())).thenReturn(
                typologyMock);

        when(typologyAssemblerMock.toDTO(typologyMock)).thenReturn(
                typologyDTOMock);

        when(typologyDTOMock.add(any(Link.class))).thenReturn(typologyDTOMock);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(typologyDTOMock, HttpStatus.CREATED);

        //Act
        ResponseEntity<Object> actual =
                typologyController.createTypology(new TypologyDTO(
                        "Fixed"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTypologySuccess() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        Optional<Typology> typologyMock = Optional.of(mock(Typology.class));

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);

        when(typologyServiceMock.findTypologyByDescription(
                anyString())).thenReturn(typologyMock);

        when(typologyAssemblerMock.toDTO(typologyMock.get())).thenReturn(
                typologyDTOMock);

        Link link = linkTo(methodOn(ImplTypologyController.class).getTypology("FIXED")).withSelfRel();
        typologyDTOMock.add(link);


        ResponseEntity<Object> expected =
                new ResponseEntity<>(typologyDTOMock,
                        HttpStatus.OK);

        //Act
        ResponseEntity<Object> actual = typologyController.getTypology(
                "FIXED");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createAndSaveTypologyWhenItAlreadyExists() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        Optional<Typology> typologyMock = Optional.of(mock(Typology.class));

        when(typologyServiceMock.findTypologyByDescription(
                anyString())).thenReturn(typologyMock);

        ResponseEntity<Object> expected =
                new ResponseEntity<>(TYPOLOGY_ALREADY_EXISTS,
                        HttpStatus.OK);

        //Act
        ResponseEntity<Object> actual =
                typologyController.createTypology(new TypologyDTO(
                        "FIXED"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTypologyFails() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        when(typologyServiceMock.findTypologyByDescription(
                anyString())).thenThrow(new IllegalArgumentException());

        ResponseEntity<Object> expected =
                new ResponseEntity<>(WRONG_INFORMATION,
                        HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual = typologyController.getTypology(
                "invalidName");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createTypologyFails() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        when(typologyServiceMock.findTypologyByDescription(
                anyString())).thenThrow(new IllegalArgumentException());

        ResponseEntity<Object> expected =
                new ResponseEntity<>(WRONG_INFORMATION,
                        HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actual =
                typologyController.createTypology(new TypologyDTO(
                        "invalidName"));

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTypologiesEmpty() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        List<TypologyDTO> typologyDTOList = new ArrayList<>();

        when(typologyServiceMock.getAll()).thenReturn(typologyDTOList);

        ResponseEntity expectedResponseEntity =
                new ResponseEntity<>("Typology doesn't exist.",
                        HttpStatus.NOT_FOUND);

        // Act
        ResponseEntity resultResponseEntity =
                typologyController.getTypologies();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }

   @Test
    void getTypologies() {
        //Arrange
        ImplAppTypologyService typologyServiceMock =
                mock(ImplAppTypologyService.class);
        TypologyAssembler typologyAssemblerMock =
                mock(TypologyAssembler.class);
        ImplTypologyController typologyController =
                new ImplTypologyController(typologyServiceMock, typologyAssemblerMock);

        List<TypologyDTO> typologyDTOList = new ArrayList<>();
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setDescription("FIXED");
        typologyDTOList.add(typologyDTO);
        Mockito.when(typologyServiceMock.getAll()).thenReturn(typologyDTOList);
        RepresentationModel<TypologyDTO> linkListExpected = new RepresentationModel<>();
        Link expectedLink = linkTo(methodOn(ImplTypologyController.class)
                .getTypology(typologyDTO.getDescription())).withRel(typologyDTO.getDescription());
                        linkListExpected.add(expectedLink);


        ResponseEntity<Object> expectedResponseEntity =
                new ResponseEntity<>(linkListExpected,
                        HttpStatus.OK);

        // Act
        ResponseEntity<Object> resultResponseEntity =
                typologyController.getTypologies();

        // Assert
        assertEquals(expectedResponseEntity, resultResponseEntity);
    }
}
