package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilterTicketsByUserIdAndTicketId {

    private final TicketRepository ticketRepository;

    public FilterTicketsByUserIdAndTicketId(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> execute(Long userId, Long ticketId) {
        return ticketRepository.findByUserIdAndTicketId(userId, ticketId);
    }
}
