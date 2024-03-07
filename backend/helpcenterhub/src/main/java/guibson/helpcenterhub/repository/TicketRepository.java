package guibson.helpcenterhub.repository;

import guibson.helpcenterhub.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    Page<Ticket> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE t.userId = ?1 AND (t.subject LIKE %?2% OR t.id = ?3)")
    Page<Ticket> findByUserIdAndSubjectContainingOrId(Long userId, String subject, Long ticketId, Pageable pageable);
}
