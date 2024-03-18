package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.dto.UserDTO;
import guibson.helpcenterhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserRepository userRepository;

    @Autowired
    public UserInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public UserDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;

        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            email = oAuth2User.getAttribute("email");
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        
        return userDTO;
    }
}
