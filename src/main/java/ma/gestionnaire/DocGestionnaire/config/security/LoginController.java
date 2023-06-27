package ma.gestionnaire.DocGestionnaire.config.security;

import jakarta.validation.Valid;
import ma.gestionnaire.DocGestionnaire.entities.User;
import ma.gestionnaire.DocGestionnaire.entities.UserAuthority;
import ma.gestionnaire.DocGestionnaire.repositories.UserRepository;
import ma.gestionnaire.DocGestionnaire.requests.LoginRequest;
import ma.gestionnaire.DocGestionnaire.requests.SignupRequest;
import ma.gestionnaire.DocGestionnaire.utilities.AuthResponse;
import ma.gestionnaire.DocGestionnaire.utilities.CustomResponse;
import ma.gestionnaire.DocGestionnaire.utilities.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(), request.password()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest()
                    .body(new CustomResponse<>("Error : Username or password is incorrect", null));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new IllegalArgumentException("password and password confirmation doesn't match!");
        if (userRepository.existsByName(request.getName()))
            throw new IllegalArgumentException(
                    "an other user has already signed up with this name " + request.getName());
        if (userRepository.existsByUsername(request.getUsername()))
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse<String>("Error: Username is already taken!", null));
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role("USER")
                .enabled(true)
                .userAuthorities(new HashSet<>())
                .name(request.getName())
                .build();
        user.addAuthority("READ");
        userRepository.save(user);
        return ResponseEntity.ok(new CustomResponse<>("User registered successfully!", user.getUsername()));
    }

}
