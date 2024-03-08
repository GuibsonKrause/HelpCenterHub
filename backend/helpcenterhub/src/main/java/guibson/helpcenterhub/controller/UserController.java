package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.dto.LoginDTO;
import guibson.helpcenterhub.dto.UserDTO;
import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.domain.usecase.AuthenticateUser;
import guibson.helpcenterhub.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticateUser authenticateUser;

    @Autowired
    public UserController(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody LoginDTO loginDTO) {
        User user = authenticateUser.execute(loginDTO.getEmail(), loginDTO.getPassword());
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }
}
