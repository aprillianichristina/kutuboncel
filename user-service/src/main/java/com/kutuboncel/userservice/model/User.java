import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotEmpty(message = "Password is required")
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = encryptPassword(password);
    }

    public boolean isPasswordValid(String password) {

        return password.equals(this.password);
    }


    private String encryptPassword(String password) {

        return password;
    }
}

