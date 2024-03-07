package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.TicketStatus;
import guibson.helpcenterhub.dto.TicketStatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketStatusMapper {
    TicketStatusMapper INSTANCE = Mappers.getMapper(TicketStatusMapper.class);

    default TicketStatusDTO ticketStatusToDTO(TicketStatus status) {
        TicketStatusDTO dto = new TicketStatusDTO();
        dto.setStatus(status.name());
        return dto;
    }

    default TicketStatus dtoToTicketStatus(TicketStatusDTO dto) {
        return TicketStatus.valueOf(dto.getStatus());
    }
}
