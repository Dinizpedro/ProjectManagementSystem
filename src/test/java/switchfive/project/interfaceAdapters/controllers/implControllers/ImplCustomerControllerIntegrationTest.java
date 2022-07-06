package switchfive.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switchfive.project.dtos.CustomerDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImplCustomerControllerIntegrationTest {
    final String WRONG_INFORMATION = "Wrong information";
    final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";

    //@Autowired private ImplCustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    //@BeforeEach public void setUp() {
    //    MockitoAnnotations.openMocks(this);
    //}

    /*@Test void getCustomerAPI_Success() throws Exception {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("TestCustomer2");
        Customer customer =
                new Customer(CustomerName.create(
                        "TestCustomer2"));
        this.customerRepository.saveCustomer(customer);

        String expected = "{\"customerName\":\"TestCustomer2\"}";

        //Act
        MvcResult result = mockMvc.perform(get("/api/customers/TestCustomer2"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String actual = result.getResponse().getContentAsString();

        //Assert
        Assertions.assertEquals(expected, actual);
    }*/

    @Test
    void getCustomerAPI_CustomerNotFound() throws Exception {

        MvcResult result =
                this.mockMvc.perform(get("/api/customers" +
                                "/TestCustomer1904"))
                        .andDo(print()).andExpect(status().isNotFound())
                        .andReturn();

        String response = result.getResponse().getContentAsString();

        Assertions.assertEquals(CUSTOMER_DOES_NOT_EXIST, response);
    }

    @Test
    void getCustomerAPI_invalidCustomerInput() throws Exception {
        MvcResult result =
                mockMvc.perform(get("/api/customers/   ")).andDo(print())
                        .andExpect(status().isBadRequest()).andReturn();

        String response = result.getResponse().getContentAsString();

        Assertions.assertEquals(WRONG_INFORMATION, response);
    }

    @Test
    void createCustomerAPI_Success() throws Exception {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("TestCustomer3");

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api" +
                                "/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createCustomerAPI_CustomerAlreadyExists() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("TestCustomer4");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void createCustomerAPI_invalidCustomerInput() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(null))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
