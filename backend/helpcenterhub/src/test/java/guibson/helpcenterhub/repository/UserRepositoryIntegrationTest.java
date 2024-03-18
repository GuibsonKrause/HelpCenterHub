/*package guibson.helpcenterhub.repository;

import static org.junit.jupiter.api.Assertions.*;

import guibson.helpcenterhub.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test") // Use o perfil de teste
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User"); // Adicione esta linha para definir o nome do usuário
        user.setPassword("test123"); // Imagine isso como sendo um hash de senha
        userRepository.save(user);
    }

    @Test
    void whenFindByEmail_thenReturnUser() {
        User found = userRepository.findByEmail(user.getEmail());

        assertNotNull(found);
        assertEquals(user.getEmail(), found.getEmail());
    }

    @Test
    void whenFindByEmail_thenReturnEmpty() {
        User found = userRepository.findByEmail("nonexistent@example.com");

        assertNull(found, "User should not be found for non-existent email");
    }
}
*/