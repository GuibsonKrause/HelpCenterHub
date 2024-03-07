package guibson.helpcenterhub.repository;

import guibson.helpcenterhub.domain.entities.TicketFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketFeedbackRepository extends JpaRepository<TicketFeedback, Long> {
    
}
