package Telecom.SubscriptionService.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestTemplateTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long userId;

    @BeforeEach
    public void setup() throws Exception {
        Map<String, Object> userPayload = new HashMap<>();
        userPayload.put("name", "Test User");
        userPayload.put("email", "test@example.com");
        userPayload.put("contact", 1234567890);
        userPayload.put("address", "Test Address");

        String result = mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userPayload)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        System.out.println("Setup result: " + result);
    }

    @Test
    public void testGetUserTickets() throws Exception {
        mockMvc.perform(get("/api/user/tickets/1"))
                .andExpect(status().isOk());
    }
}
