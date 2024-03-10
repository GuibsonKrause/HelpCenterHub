package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.domain.usecase.CreateTicket;
import guibson.helpcenterhub.domain.usecase.ListTicketsByUserWithFilter;
import guibson.helpcenterhub.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final CreateTicket createTicket;
    private final ListTicketsByUserWithFilter listTicketsByUserWithFilter;

    @Autowired
    public TicketController(CreateTicket createTicket, ListTicketsByUserWithFilter listTicketsByUserWithFilter) {
        this.createTicket = createTicket;
        this.listTicketsByUserWithFilter = listTicketsByUserWithFilter;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = createTicket.execute(ticketDTO.getUserId(), ticketDTO.getSubject(), ticketDTO.getDescription());
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/user/tickets")
    public ResponseEntity<Page<Ticket>> listUserTicketsWithFilter(
            @RequestParam Long userId,
            @RequestParam(required = false, defaultValue = "") String filter,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Ticket> tickets = listTicketsByUserWithFilter.execute(userId, filter, pageable);
        return ResponseEntity.ok(tickets);
    }
}
