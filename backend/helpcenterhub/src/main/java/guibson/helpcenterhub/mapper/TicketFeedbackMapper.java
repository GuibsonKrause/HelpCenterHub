package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.TicketFeedback;
import guibson.helpcenterhub.dto.TicketFeedbackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketFeedbackMapper {
    TicketFeedbackMapper INSTANCE = Mappers.getMapper(TicketFeedbackMapper.class);

    TicketFeedbackDTO ticketFeedbackToTicketFeedbackDTO(TicketFeedback ticketFeedback);
    TicketFeedback ticketFeedbackDTOToTicketFeedback(TicketFeedbackDTO ticketFeedbackDTO);
}
