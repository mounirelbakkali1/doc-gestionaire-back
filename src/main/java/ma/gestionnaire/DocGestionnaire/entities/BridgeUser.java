package ma.gestionnaire.DocGestionnaire.entities;

import java.util.HashSet;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BridgeUser {
    private String username;
    private Set<Role> roles;
    private String name;

    public BridgeUser(User user) {
        this.username = user.getUsername();
        this.roles = new HashSet<>(user.getRoles());
        this.name = user.getName();
    }
}
