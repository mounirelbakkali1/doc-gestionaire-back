package ma.gestionnaire.DocGestionnaire.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;
    @NotBlank
    private String confirmPassword;
}
