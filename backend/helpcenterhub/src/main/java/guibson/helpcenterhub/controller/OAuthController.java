/*package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.usecase.ProcessUserLogin;
import guibson.helpcenterhub.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/google")
public class OAuthController {

    @Autowired
    private ProcessUserLogin auth;

    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleAuth(@RequestParam("code") String code) {
        UserDTO userDTO = auth.processUserAuthentication(code);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.badRequest().body("Error processing Google login");
        }
    }
}
*/