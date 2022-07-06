package switchfive.project.applicationServices.appServices.implAppServices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfive.project.applicationServices.assemblers.implAssemblers.TypologyAssembler;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.factories.implFactories.ImplTypologyFactory;
import switchfive.project.dtos.TypologyDTO;
import switchfive.project.applicationServices.iRepositories.ITypologyRepository;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.factories.iFactories.ITypologyFactory;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplAppTypologyServiceTest {


    @Mock
     ITypologyRepository iTypologyRepository;

    @Mock
    ITypologyFactory iTypologyFactory;

    @InjectMocks
    ImplAppTypologyService implAppTypologyService;


    @Test
    void newTypologyIsAdded() {
        //Arrange
        Typology typologyMock = mock(Typology.class);
        when(iTypologyRepository.findTypology(any())).thenReturn(Optional.empty());
        when(iTypologyFactory.createTypology(any())).thenReturn(typologyMock);

        Optional<Typology> expected = Optional.of(typologyMock);

        // Act

        Optional<Typology> result = Optional.ofNullable(implAppTypologyService.addNewTypology("FIXED COST"));

        // Assert
        assertEquals(expected,result);


    }

   @Test
    void newTypologyAlreadyExists() {
        //arrange
        ITypologyRepository typologyRepository = mock(ITypologyRepository.class);
        ImplTypologyFactory typologyFactory = mock(ImplTypologyFactory.class);
        TypologyDescription typologyDescription = TypologyDescription.create("Fixed Cost");
        Typology typology = mock(Typology.class);
        when(typologyRepository.findTypology(typologyDescription)).thenReturn(Optional.of(typology));

        ImplAppTypologyService typologyService = new ImplAppTypologyService(typologyRepository, typologyFactory);

        // Act
       assertThrows(IllegalArgumentException.class,
               () -> typologyService.addNewTypology("Fixed Cost"));
    }

    @Test
    void getAllTypologies() {
        // Arrange
        List<Typology> typologyList = new ArrayList<>();
        TypologyDescription description = TypologyDescription.create("Valor teste");
        Typology typology = new Typology(description);
        typologyList.add(typology);
        when(iTypologyRepository.findAll()).thenReturn(typologyList);

        TypologyDTO dto = new TypologyDTO();
        List<TypologyDTO> expectedList = new ArrayList<>();
        expectedList.add(dto);


        // Assert
        assertEquals(expectedList,implAppTypologyService.getAll());
     }
}
