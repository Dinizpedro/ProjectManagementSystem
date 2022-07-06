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
import switchfive.project.dtos.TypologyDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ImplTypologyControllerIntegrationTest {

    final String WRONG_INFORMATION = "Wrong information";
    final String TYPOLOGY_DOES_NOT_EXIST = "Typology doesn't exist";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTypologyAPI_NotFound() throws Exception {

        MvcResult result =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/typologies"  + "/notExistantTypology")).andExpect(status().isNotFound()).andReturn();
        String response = result.getResponse().getContentAsString();

        assertEquals(TYPOLOGY_DOES_NOT_EXIST, response);
    }

    @Test
    void getTypologyAPI_invalidTypology() throws Exception {
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/api/typologies/       ")).andDo(print()).andExpect(status().isBadRequest()).andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals(WRONG_INFORMATION, response);
    }

    @Test
    void createTypologyAPI_invalidTypology() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/typologies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(null))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void createTypologyAPI() throws Exception {

        TypologyDTO typologyDTO = new TypologyDTO();

        typologyDTO.setDescription("FIXED COST");

        mockMvc.perform(MockMvcRequestBuilders.post("/api" + "/typologies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(typologyDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createTypologyAPI_TypologyAlreadyExists() throws Exception {
        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.setDescription("TestTypology");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/typologies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(typologyDTO))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/typologies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(typologyDTO))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
