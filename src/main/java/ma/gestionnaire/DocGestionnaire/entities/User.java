package ma.gestionnaire.DocGestionnaire.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "username", unique = true)
    String username;
    String password;
    boolean enabled = true;

    private String role;
    private String name;

    @OneToMany(cascade = ALL, fetch = EAGER)
    Set<UserAuthority> userAuthorities = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // copy constructor
    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.name = user.name;
        this.enabled = user.enabled;
        this.role = user.role;
        this.userAuthorities = new HashSet<>(user.userAuthorities);
    }

    public void addAuthority(String authority) {
        this.userAuthorities.add(new UserAuthority(this, authority));
    }

}
