package guibson.helpcenterhub.service;

import guibson.helpcenterhub.domain.dto.UserDto;
import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Importações relevantes para a integração com o Google

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Supondo que você tenha uma classe GoogleOAuthClient para a integração com o Google
    @Autowired
    private GoogleOAuthClient googleOAuthClient;

    public UserDto authenticateUserWithGoogle(String code) {
        // Lógica para trocar o código por um token de acesso e buscar os detalhes do usuário com o Google
        GoogleUser googleUser = googleOAuthClient.getUserInfo(code);

        // Verifica se o usuário já existe, se não, cria um novo
        User user = userRepository.findByEmail(googleUser.getEmail()).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(googleUser.getEmail());
            newUser.setName(googleUser.getName());
            // Definir mais campos conforme necessário
            return userRepository.save(newUser);
        });

        // Converter User para UserDto e retornar
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        // Implementação da conversão
        return new UserDto(user.getName(), user.getEmail());
    }
}
