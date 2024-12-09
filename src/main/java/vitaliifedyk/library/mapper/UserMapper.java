package vitaliifedyk.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vitaliifedyk.library.dto.CreateUserDto;
import vitaliifedyk.library.dto.ReadUserDto;
import vitaliifedyk.library.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserDto createUserDto);
    ReadUserDto toReadUserDto(User user);
    void updateUserFromCreateUserDto(CreateUserDto createUserDto, @MappingTarget User user);
}