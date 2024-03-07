package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.UpdateType;
import guibson.helpcenterhub.dto.UpdateTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateTypeMapper {
    UpdateTypeMapper INSTANCE = Mappers.getMapper(UpdateTypeMapper.class);

    default UpdateTypeDTO updateTypeToUpdateTypeDTO(UpdateType updateType) {
        if ( updateType == null ) {
            return null;
        }
        UpdateTypeDTO dto = new UpdateTypeDTO();
        dto.setUpdateType(updateType.name());
        return dto;
    }

    default UpdateType updateTypeDTOToUpdateType(UpdateTypeDTO dto) {
        if ( dto == null || dto.getUpdateType() == null ) {
            return null;
        }
        return UpdateType.valueOf(dto.getUpdateType());
    }
}
