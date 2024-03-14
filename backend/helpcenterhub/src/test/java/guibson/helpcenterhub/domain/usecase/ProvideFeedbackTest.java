package guibson.helpcenterhub.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import guibson.helpcenterhub.domain.entities.TicketFeedback;
import guibson.helpcenterhub.dto.TicketFeedbackDTO;
import guibson.helpcenterhub.repository.TicketFeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

public class ProvideFeedbackTest {

    @Mock
    private TicketFeedbackRepository ticketFeedbackRepository;

    @InjectMocks
    private ProvideFeedback provideFeedback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenProvideFeedback_thenFeedbackIsSaved() {
        // Arrange
        TicketFeedback mockFeedback = new TicketFeedback();
        mockFeedback.setId(1L);
        mockFeedback.setUserId(1L);
        mockFeedback.setTicketId(1L);
        mockFeedback.setRating(5);
        mockFeedback.setComment("Excellent service");
        mockFeedback.setCreatedAt(LocalDateTime.now());

        when(ticketFeedbackRepository.save(any(TicketFeedback.class))).thenReturn(mockFeedback);

        // Act
        TicketFeedbackDTO result = provideFeedback.execute(1L, 1L, 5, "Excellent service");

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getTicketId());
        assertEquals(1L, result.getUserId());
        assertEquals(5, result.getRating());
        assertEquals("Excellent service", result.getComment());
    }
}
