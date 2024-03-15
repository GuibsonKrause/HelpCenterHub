package guibson.helpcenterhub.controller;

import guibson.helpcenterhub.domain.entities.Role;
import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.domain.entities.User;
import guibson.helpcenterhub.domain.usecase.CloseTicket;
import guibson.helpcenterhub.domain.usecase.CreateTicket;
import guibson.helpcenterhub.dto.TicketDTO;
import guibson.helpcenterhub.repository.UserRepository;
import guibson.helpcenterhub.service.SseService;
import guibson.helpcenterhub.domain.usecase.FilterTicketsByUserIdAndDescription;
import guibson.helpcenterhub.domain.usecase.FilterTicketsByUserIdAndTicketId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final CreateTicket createTicket;
    private final FilterTicketsByUserIdAndTicketId filterTicketsByUserIdAndTicketId;
    private final FilterTicketsByUserIdAndDescription filterTicketsByUserIdAndDescription;

    @Autowired
    private final CloseTicket closeTicket;
    @Autowired
    private UserRepository userRepository;
    private final SseService sseService;

    @Autowired
    public TicketController(CreateTicket createTicket,
            FilterTicketsByUserIdAndTicketId filterTicketsByUserIdAndTicketId,
            FilterTicketsByUserIdAndDescription filterTicketsByUserIdAndDescription,
            CloseTicket closeTicket, SseService sseService, UserRepository userRepository) {
        this.createTicket = createTicket;
        this.filterTicketsByUserIdAndTicketId = filterTicketsByUserIdAndTicketId;
        this.filterTicketsByUserIdAndDescription = filterTicketsByUserIdAndDescription;
        this.closeTicket = closeTicket;
        this.sseService = sseService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = createTicket.execute(ticketDTO.getUserId(), ticketDTO.getSubject(),
                ticketDTO.getDescription());
        sseService.sendTicketToAll(createdTicket);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> listUserTicketsByFilter(
            @PathVariable Long userId,
            @RequestParam(required = false) String filter,
            @PageableDefault Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 10);
        }

        if (filter != null && filter.matches("\\d+")) {
            Long ticketId = Long.parseLong(filter);
            Optional<Ticket> ticket = filterTicketsByUserIdAndTicketId.execute(userId, ticketId);
            List<Ticket> ticketList = ticket.map(Collections::singletonList).orElseGet(Collections::emptyList);
            Page<Ticket> ticketPage = new PageImpl<>(ticketList, pageable, ticketList.size());
            return ResponseEntity.ok(ticketPage);
        } else {
            Page<Ticket> tickets;
            if (filter != null) {
                tickets = filterTicketsByUserIdAndDescription.execute(userId, filter, pageable);
            } else {
                tickets = filterTicketsByUserIdAndDescription.execute(userId, "", pageable);
            }
            return ResponseEntity.ok(tickets);
        }
    }

    @PatchMapping("/{ticketId}/close")
    // @RequestAttribute("userId") Long managerId,
    public ResponseEntity<?> closeTicket(@PathVariable Long ticketId) {
        Long managerId = (long) 1;
        if (managerId == null || ticketId == null) {
            return ResponseEntity.badRequest().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);
        if (user != null && user.getRoles().contains(Role.MANAGER)) {
            Optional<Ticket> closedTicket = closeTicket.execute(managerId, ticketId);
            return closedTicket.map(ticket -> ResponseEntity.ok().build())
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
