package ma.gestionnaire.DocGestionnaire.utilities;

import ma.gestionnaire.DocGestionnaire.entities.User;
import ma.gestionnaire.DocGestionnaire.entities.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public UserDTO toDto(User user) {
        String username = user.getUsername();
        String role = user.getRole();
        String name = user.getName();
        return new UserDTO(name, username, role);
    }

}
