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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
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
        this.roles = new HashSet<>(user.roles);
        this.userAuthorities = new HashSet<>(user.userAuthorities);
    }

    public void addAuthority(String authority) {
        this.userAuthorities.add(new UserAuthority(this, authority));
    }

    public void addRole(String role) {
        this.roles.add(new Role(role));
    }

}
