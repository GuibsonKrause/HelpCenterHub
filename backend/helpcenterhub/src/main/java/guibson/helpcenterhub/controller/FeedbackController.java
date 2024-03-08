package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.dto.TicketFeedbackDTO;
import guibson.helpcenterhub.domain.usecase.ProvideFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final ProvideFeedback provideFeedback;

    @Autowired
    public FeedbackController(ProvideFeedback provideFeedback) {
        this.provideFeedback = provideFeedback;
    }

    @PostMapping
    public ResponseEntity<TicketFeedbackDTO> provideFeedback(@RequestBody TicketFeedbackDTO feedbackDTO) {
        TicketFeedbackDTO createdFeedback = provideFeedback.execute(feedbackDTO.getUserId(), feedbackDTO.getTicketId(), feedbackDTO.getRating(), feedbackDTO.getComment());
        return ResponseEntity.ok(createdFeedback);
    }
}
