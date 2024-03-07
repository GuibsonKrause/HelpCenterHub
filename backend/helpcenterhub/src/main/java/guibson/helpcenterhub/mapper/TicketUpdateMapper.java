package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.TicketUpdate;
import guibson.helpcenterhub.dto.TicketUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketUpdateMapper {
    TicketUpdateMapper INSTANCE = Mappers.getMapper(TicketUpdateMapper.class);

    TicketUpdateDTO ticketUpdateToTicketUpdateDTO(TicketUpdate ticketUpdate);

    TicketUpdate ticketUpdateDTOToTicketUpdate(TicketUpdateDTO ticketUpdateDTO);
}
