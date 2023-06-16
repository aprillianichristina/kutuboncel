import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationRequest {
    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotEmpty(message = "Password is required")
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

