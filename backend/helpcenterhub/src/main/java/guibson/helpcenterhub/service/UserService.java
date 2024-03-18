package guibson.helpcenterhub.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserFromAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String email = oAuth2User.getAttribute("email");
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            throw new RuntimeException("Authentication principal is not of expected type");
        }
    }
}
