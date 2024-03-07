package guibson.helpcenterhub.repository;

import guibson.helpcenterhub.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para buscar um usuário pelo email, útil para autenticação e verificação de existência.
    User findByEmail(String email);

}
