package guibson.helpcenterhub.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class FilterTicketsByUserIdAndTicketIdTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private FilterTicketsByUserIdAndTicketId filterTicketsByUserIdAndTicketId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFilterTicketByUserIdAndTicketId_thenCorrectTicketIsReturned() {
        // Arrange
        Ticket expectedTicket = new Ticket();
        expectedTicket.setId(1L);
        expectedTicket.setUserId(1L);

        when(ticketRepository.findByUserIdAndTicketId(anyLong(), anyLong())).thenReturn(Optional.of(expectedTicket));

        // Act
        Optional<Ticket> result = filterTicketsByUserIdAndTicketId.execute(1L, 1L);

        // Assert
        assertTrue(result.isPresent(), "Expected ticket to be present");
        assertEquals(expectedTicket, result.get(), "Expected ticket to match the mock response");
    }

    @Test
    void whenFilterTicketByUserIdAndTicketIdAndTicketDoesNotExist_thenNoTicketIsReturned() {
        // Arrange
        when(ticketRepository.findByUserIdAndTicketId(anyLong(), anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Ticket> result = filterTicketsByUserIdAndTicketId.execute(1L, 999L); // Using an unlikely ticket ID for
                                                                                      // clarity

        // Assert
        assertFalse(result.isPresent(), "Expected no ticket to be returned");
    }
}
