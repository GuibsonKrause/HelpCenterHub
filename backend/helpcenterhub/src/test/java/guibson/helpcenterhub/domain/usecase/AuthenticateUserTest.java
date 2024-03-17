/*package guibson.helpcenterhub.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticateUserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProcessUserLogin authenticateUser;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("encryptedPassword"); // Assuming this would be the encrypted form
        when(userRepository.findByEmail(anyString())).thenReturn(mockUser);
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(mockUser.getEmail(), mockUser.getPassword()));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
    }

    @Test
    void whenAuthenticateUser_thenSuccess() {
        // Act
        User result = authenticateUser.execute("user@example.com", "password");

        // Assert
        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
        // Checks if authentication is stored in the security context
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("user@example.com", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Test
    void whenAuthenticateUserWithBadCredentials_thenThrowException() {
        // Arrange
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // Act & Assert
        assertThrows(BadCredentialsException.class,
                () -> authenticateUser.execute("user@example.com", "wrongpassword"));
    }
}
*/