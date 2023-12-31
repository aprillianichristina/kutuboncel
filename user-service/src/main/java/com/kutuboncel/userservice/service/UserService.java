import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    public User registerUser(UserRegistrationRequest request) {
        validateUserRegistrationRequest(request);

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encryptPassword(request.getPassword()));

        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        String url = "http://localhost:8080/users/" + userId;
        return restTemplate.getForObject(url, User.class);
    }

    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        validateUserUpdateRequest(request);

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    private void validateUserRegistrationRequest(UserRegistrationRequest request) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new IllegalArgumentException("Name must not be empty");
        }

        if (StringUtils.isEmpty(request.getEmail()) || !isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (StringUtils.isEmpty(request.getPassword())) {
            throw new IllegalArgumentException("Password must not be empty");
        }
    }

    private void validateUserUpdateRequest(UserUpdateRequest request) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new IllegalArgumentException("Name must not be empty");
        }

        if (StringUtils.isEmpty(request.getEmail()) || !isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    private boolean isValidEmail(String email) {
    }

    private String encryptPassword(String password) {
    }
}
