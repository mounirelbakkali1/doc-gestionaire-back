package ma.gestionnaire.DocGestionnaire.entities;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    String name;
    String username;
    Set<Role> roles;
}
