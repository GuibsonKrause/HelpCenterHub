package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.usecase.CreateTicket;
import guibson.helpcenterhub.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final CreateTicket createTicket;

    @Autowired
    public TicketController(CreateTicket createTicket) {
        this.createTicket = createTicket;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = createTicket.execute(ticketDTO.getUserId(), ticketDTO.getSubject(), ticketDTO.getDescription());
        return ResponseEntity.ok(createdTicket);
    }
}
