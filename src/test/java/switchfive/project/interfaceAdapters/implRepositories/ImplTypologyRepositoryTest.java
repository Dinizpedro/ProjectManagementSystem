package switchfive.project.interfaceAdapters.implRepositories;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.ITypologyAssemblerJPA;
import switchfive.project.dataModel.dataJPA.TypologyJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.ITypologyRepositoryJPA;
import switchfive.project.domain.aggregates.typology.Typology;
import switchfive.project.domain.shared.valueObjects.TypologyDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class ImplTypologyRepositoryTest {

    @Mock Typology typologyMock;

    @Mock TypologyJPA typologyJPAMock;

    @Mock ITypologyAssemblerJPA typologyAssemblerJPA;

    @Mock ITypologyRepositoryJPA typologyRepositoryJPA;

    @InjectMocks ImplTypologyRepository typologyRepository;

    @Test
    void typologyExists() {
        //Arrange
        when(typologyRepositoryJPA.existsByDescription(anyString())).thenReturn(true);

        //Act
        boolean expected = typologyRepository.typologyExists(TypologyDescription.create("typology"));

        //Assert
        assertTrue(expected);
    }

    @Test
    void typologyDoesNotExists() {
        //Arrange
        when(typologyRepositoryJPA.existsByDescription(anyString())).thenReturn(false);

        //Act
        boolean expected = typologyRepository.typologyExists(TypologyDescription.create("typology"));

        //Assert
        assertFalse(expected);
    }

    @Test
    void saveNewTypologySuccessful() {
        //Arrange
        when(typologyAssemblerJPA.toData(any(Typology.class))).thenReturn(typologyJPAMock);
        when(typologyRepositoryJPA.save(any(TypologyJPA.class))).thenReturn(typologyJPAMock);

        //Act
        typologyRepository.saveNewTypology(typologyMock);

        //Assert
        verify(typologyAssemblerJPA, times(1)).toData(any(Typology.class));
        verify(typologyRepositoryJPA, times(1)).save(any(TypologyJPA.class));
    }

    @Test
    void findAllSuccessful() {
        //Arrange
        List<Typology> typologiesList = new ArrayList<>();
        typologiesList.add(typologyMock);

        List<TypologyJPA> typologiesJPAList = new ArrayList<>();
        typologiesJPAList.add(typologyJPAMock);

        when(typologyRepositoryJPA.findAll()).thenReturn(typologiesJPAList);
        when(typologyAssemblerJPA.toDomain(any(TypologyJPA.class))).thenReturn(typologyMock);

        //Act
        List<Typology> expected = typologyRepository.findAll();

        //Assert
        assertEquals(expected, typologiesList);
    }

    @Test
    void findAllEmpty() {
        //Arrange
        List<Typology> typologiesList = new ArrayList<>();

        List<TypologyJPA> typologiesJPAList = new ArrayList<>();

        when(typologyRepositoryJPA.findAll()).thenReturn(typologiesJPAList);
        when(typologyAssemblerJPA.toDomain(any(TypologyJPA.class))).thenReturn(typologyMock);

        //Act
        List<Typology> expected = typologyRepository.findAll();

        //Assert
        assertEquals(expected, typologiesList);
    }

    @Test
    void findTypologySuccessful() {
        //Arrange
        when(typologyRepositoryJPA.existsByDescription(anyString())).thenReturn(true);
        when(typologyRepositoryJPA.findByDescription(anyString())).thenReturn(Optional.of(typologyJPAMock));
        when(typologyAssemblerJPA.toDomain(any(TypologyJPA.class))).thenReturn(typologyMock);

        //Act
        Optional<Typology> expected = typologyRepository.findTypology(TypologyDescription.create("newTypology"));

        //Assert
        assertEquals(expected, Optional.of(typologyMock));
    }

    @Test
    void findTypologyEmpty() {
        //Arrange
        when(typologyRepositoryJPA.existsByDescription(anyString())).thenReturn(false);

        //Act
        Optional<Typology> expected = typologyRepository.findTypology(TypologyDescription.create("newTypo"));

        //Assert
        assertEquals(expected, Optional.empty());
    }
}
