import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserUpdateRequest {
    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name, String email) {
        this.name = name;
        this.email = email;
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
}

