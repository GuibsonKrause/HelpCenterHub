package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.TicketFeedback;
import guibson.helpcenterhub.repository.TicketFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveFeedback {

    private final TicketFeedbackRepository repository;

    @Autowired
    public RetrieveFeedback(TicketFeedbackRepository repository) {
        this.repository = repository;
    }

    public Optional<TicketFeedback> execute(Long ticketId) {
        return repository.findByTicketId(ticketId);
    }
}
