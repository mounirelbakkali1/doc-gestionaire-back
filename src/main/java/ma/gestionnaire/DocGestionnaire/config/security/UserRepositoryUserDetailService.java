package ma.gestionnaire.DocGestionnaire.config.security;

import ma.gestionnaire.DocGestionnaire.entities.User;
import ma.gestionnaire.DocGestionnaire.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserRepositoryUserDetailService implements UserDetailsService {
    final UserRepository users;

    public UserRepositoryUserDetailService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.findByUsername(username)
                .map(BridgeUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private static class BridgeUser extends User implements UserDetails {
        public BridgeUser(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return super.getUserAuthorities()
                    .stream()
                    .map(userAuth -> new SimpleGrantedAuthority(userAuth.getAuthority()))
                    .collect(Collectors.toList());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }
    }
}
