package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListTicketsByUserWithFilter {

    private final TicketRepository ticketRepository;

    public ListTicketsByUserWithFilter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Page<Ticket> execute(Long userId, String filter, Pageable pageable) {
        Long ticketId = null;
        try {
            ticketId = Long.parseLong(filter);
        } catch (NumberFormatException e) {
            
        }
        return ticketRepository.findByUserIdAndSubjectContainingOrId(userId, filter, ticketId, pageable);
    }
}
