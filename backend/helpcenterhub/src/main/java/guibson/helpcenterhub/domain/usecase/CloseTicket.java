package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.domain.entities.TicketStatus;
import guibson.helpcenterhub.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CloseTicket {

    private final TicketRepository ticketRepository;

    @Autowired
    public CloseTicket(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> execute(@NonNull Long managerId, @NonNull Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isPresent() && ticket.get().getStatus() != TicketStatus.CLOSED) {
            Ticket updatingTicket = ticket.get();
            updatingTicket.setStatus(TicketStatus.CLOSED);
            updatingTicket.setClosedAt(LocalDateTime.now());
            return Optional.of(ticketRepository.save(updatingTicket));
        }
        return Optional.empty();
    }
}
