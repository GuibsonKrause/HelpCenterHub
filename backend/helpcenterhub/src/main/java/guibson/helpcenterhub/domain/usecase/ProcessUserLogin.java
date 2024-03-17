package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.domain.entities.Role;
import guibson.helpcenterhub.repository.UserRepository;
import guibson.helpcenterhub.service.JwtTokenProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProcessUserLogin {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ProcessUserLogin(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public OAuth2User execute(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    Set<Role> roles = new HashSet<>();
                    roles.add(Role.USER); // Atribui o role USER por padrão
                    newUser.setRoles(roles);
                    return userRepository.save(newUser);
                });

        String token = jwtTokenProvider.generateToken(user.getEmail());

        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

        Map<String, Object> attributes = Map.of(
            "email", user.getEmail(),
            "name", user.getName(),
            "token", token
        );

        return new DefaultOAuth2User(authorities, attributes, "email");
    }
}
