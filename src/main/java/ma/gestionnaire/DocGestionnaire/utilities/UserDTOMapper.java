package ma.gestionnaire.DocGestionnaire.utilities;

import ma.gestionnaire.DocGestionnaire.entities.User;
import ma.gestionnaire.DocGestionnaire.entities.UserDTO;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public UserDTO toDto(User user) {
        String username = user.getUsername();
        Set roles = new HashSet<>(user.getRoles());
        String name = user.getName();
        return new UserDTO(name, username, roles);
    }

}
