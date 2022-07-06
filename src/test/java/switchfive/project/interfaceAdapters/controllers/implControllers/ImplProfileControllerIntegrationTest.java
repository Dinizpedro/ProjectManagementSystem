package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.dtos.ProfileCreationDTO;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImplProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewProfile() throws Exception {
        // Arrange
        ProfileCreationDTO profileCreationDTO = new ProfileCreationDTO();
        profileCreationDTO.description = "Junior";

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void addNewProfileFail() throws Exception {
        // Arrange
        ProfileCreationDTO profileCreationDTO = new ProfileCreationDTO();
        profileCreationDTO.description = "Junior";

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addNewProfileBadDescription() throws Exception {
        // Arrange
        ProfileCreationDTO profileCreationDTO = new ProfileCreationDTO();
        profileCreationDTO.description = "";

        //Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Invalid Description", response);
    }

    @Test
    void getProfileFound() throws Exception {
        ProfileCreationDTO profileCreationDTO = new ProfileCreationDTO();
        profileCreationDTO.description = "Junior";

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/Junior"))
                .andExpect(status().isOk());
    }


    @Test
    void getProfileNotFound() throws Exception {
        //Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/Junior"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProfileDescriptionObjFail() throws Exception {
        //Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/profiles/qwertyuiopasdfghjklxcv" +
                        "bnm,sldfuiahsdfiohasfjasiofjasodfijpdfjaspfioasjdfpoasidjkfmasdokflçma" +
                        "sdfçasdkfjmasiodfjasmdo+fkamsldf+askdfa"))
                .andExpect(status().isBadRequest());
    }


}
