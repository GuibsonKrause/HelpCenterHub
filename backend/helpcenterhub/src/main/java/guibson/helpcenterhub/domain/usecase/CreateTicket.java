package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.domain.entities.TicketStatus;
import guibson.helpcenterhub.dto.TicketDTO;
import guibson.helpcenterhub.mapper.TicketMapper;
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

    public TicketDTO execute(Long userId, String subject, String description) {
        Ticket newTicket = new Ticket();
        newTicket.setUserId(userId);
        newTicket.setSubject(subject);
        newTicket.setDescription(description);
        newTicket.setStatus(TicketStatus.OPEN);
        newTicket.setCreatedAt(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(newTicket);
        return TicketMapper.INSTANCE.ticketToTicketDTO(savedTicket);
    }
}
