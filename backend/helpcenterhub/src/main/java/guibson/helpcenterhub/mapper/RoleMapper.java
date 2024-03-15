package guibson.helpcenterhub.mapper;

import guibson.helpcenterhub.domain.entities.Role;
import guibson.helpcenterhub.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    default RoleDTO roleToRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setName(role.name());
        return dto;
    }

    default Role roleDTOToRole(RoleDTO roleDTO) {
        if (roleDTO == null || roleDTO.getName() == null) {
            return null;
        }
        return Role.valueOf(roleDTO.getName());
    }
}
