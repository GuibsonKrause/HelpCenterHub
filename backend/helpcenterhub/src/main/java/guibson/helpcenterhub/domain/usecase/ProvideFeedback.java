package guibson.helpcenterhub.domain.usecase;

import guibson.helpcenterhub.domain.entities.TicketFeedback;
import guibson.helpcenterhub.repository.TicketFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProvideFeedback {

    private final TicketFeedbackRepository ticketFeedbackRepository;

    @Autowired
    public ProvideFeedback(TicketFeedbackRepository ticketFeedbackRepository) {
        this.ticketFeedbackRepository = ticketFeedbackRepository;
    }

    public TicketFeedback execute(Long userId, Long ticketId, int rating, String comment) {
        TicketFeedback feedback = new TicketFeedback();
        feedback.setUserId(userId);
        feedback.setTicketId(ticketId);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setCreatedAt(LocalDateTime.now());
        return ticketFeedbackRepository.save(feedback);
    }
}
