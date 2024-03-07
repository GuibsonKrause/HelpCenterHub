package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.Ticket;
import guibson.helpcenterhub.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDTO ticketToTicketDTO(Ticket ticket);
    Ticket ticketDTOToTicket(TicketDTO ticketDTO);
}
