package ma.gestionnaire.DocGestionnaire.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserAuthority {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    User user;
    String authority;

    public UserAuthority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }
}
