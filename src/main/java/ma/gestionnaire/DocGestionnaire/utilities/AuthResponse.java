package ma.gestionnaire.DocGestionnaire.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.gestionnaire.DocGestionnaire.entities.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String username;
    private Set<Role> roles ;
    private String accessToken;
}
