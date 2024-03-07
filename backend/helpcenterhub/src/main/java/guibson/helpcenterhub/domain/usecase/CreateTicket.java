package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.domain.entities.TicketStatus;
import guibson.helpcenterhub.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CreateTicket {

    private final TicketRepository ticketRepository;

    @Autowired
    public CreateTicket(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket execute(Long userId, String subject, String description) {
        Ticket newTicket = new Ticket();
        newTicket.setUserId(userId);
        newTicket.setSubject(subject);
        newTicket.setDescription(description);
        newTicket.setStatus(TicketStatus.OPEN); // Corrigido para usar TicketStatus diretamente
        newTicket.setCreatedAt(LocalDateTime.now()); // Configura a data e hora de criação do ticket para agora
        return ticketRepository.save(newTicket); // Salva o novo ticket no banco de dados e o retorna
    }
}
