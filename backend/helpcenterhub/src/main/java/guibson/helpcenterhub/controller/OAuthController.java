package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.dto.UserDTO;
import guibson.helpcenterhub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/auth/google/callback")
    public ResponseEntity<UserDTO> handleGoogleCallback(@RequestParam String code) {
        UserDTO userDto = authService.authenticateUserWithGoogle(code);
        return ResponseEntity.ok(userDto);
    }
}
