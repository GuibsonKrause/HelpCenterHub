package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
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
        newTicket.setStatus(Ticket.TicketStatus.OPEN); // Assume que existe uma enumeração para os estados do Ticket
        newTicket.setCreatedAt(LocalDateTime.now()); // Configura a data e hora de criação do ticket para agora
        // Aqui você pode adicionar mais lógica conforme necessário, por exemplo, validações específicas
        return ticketRepository.save(newTicket); // Salva o novo ticket no banco de dados e o retorna
    }
}
