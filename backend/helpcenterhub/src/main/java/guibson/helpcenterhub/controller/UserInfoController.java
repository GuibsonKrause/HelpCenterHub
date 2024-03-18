package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.dto.UserDTO;
import guibson.helpcenterhub.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserService userService;

    @Autowired
    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUserFromAuthentication(authentication);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        
        return userDTO;
    }
}
