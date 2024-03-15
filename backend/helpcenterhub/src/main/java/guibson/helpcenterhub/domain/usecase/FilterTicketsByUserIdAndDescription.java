package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilterTicketsByUserIdAndDescription {

    private final TicketRepository ticketRepository;

    public FilterTicketsByUserIdAndDescription(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Page<Ticket> execute(Long userId, String description, Pageable pageable) {
        return ticketRepository.findByUserIdAndDescriptionContaining(userId, description, pageable);
    }
}
