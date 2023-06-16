import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterUser() throws Exception {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest("John Doe", "johndoe@example.com", "password");
        User registeredUser = new User(1L, "John Doe", "johndoe@example.com");

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenReturn(registeredUser);

        String requestBody = objectMapper.writeValueAsString(registrationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(registeredUser.getId()))
                .andExpect(jsonPath("$.name").value(registeredUser.getName()))
                .andExpect(jsonPath("$.email").value(registeredUser.getEmail()));

        verify(userService, times(1)).registerUser(any(UserRegistrationRequest.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserById() throws Exception {
        long userId = 1L;
        User user = new User(userId, "John Doe", "johndoe@example.com");

        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));

        verify(userService, times(1)).getUserById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testUpdateUser() throws Exception {
        long userId = 1L;
        UserUpdateRequest updateRequest = new UserUpdateRequest("John Doe Jr.", "johndoe@example.com");
        User updatedUser = new User(userId, "John Doe Jr.", "johndoe@example.com");

        when(userService.updateUser(eq(userId), any(UserUpdateRequest.class))).thenReturn(updatedUser);

        String requestBody = objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedUser.getId()))
                .andExpect(jsonPath("$.name").value(updatedUser.getName()))
                .andExpect(jsonPath("$.email").value(updatedUser.getEmail()));

        verify(userService, times(1)).updateUser(eq(userId), any(UserUpdateRequest.class));
        verifyNoMoreInteractions(userService);
    }

    // Add more tests for other endpoints and scenarios

}

