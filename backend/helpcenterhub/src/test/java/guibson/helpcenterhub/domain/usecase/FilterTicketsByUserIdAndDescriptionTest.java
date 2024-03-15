package guibson.helpcenterhub.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

public class FilterTicketsByUserIdAndDescriptionTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private FilterTicketsByUserIdAndDescription filterTicketsByUserIdAndDescription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFilterTicketsByUserIdAndDescription_thenCorrectTicketsAreReturned() {
        // Arrange
        Ticket ticketOne = new Ticket();
        Ticket ticketTwo = new Ticket();
        List<Ticket> tickets = Arrays.asList(ticketOne, ticketTwo);
        Page<Ticket> pagedResponse = new PageImpl<>(tickets);

        when(ticketRepository.findByUserIdAndDescriptionContaining(anyLong(), anyString(), any(PageRequest.class)))
                .thenReturn(pagedResponse);

        // Act
        Page<Ticket> result = filterTicketsByUserIdAndDescription.execute(1L, "test", PageRequest.of(0, 5));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().containsAll(tickets));
    }
}
