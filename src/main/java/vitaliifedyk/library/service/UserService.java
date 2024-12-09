package vitaliifedyk.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vitaliifedyk.library.dto.CreateUserDto;
import vitaliifedyk.library.dto.ReadUserDto;

public interface UserService {
    ReadUserDto createUser(CreateUserDto createUserDto);
    ReadUserDto readUser(Long id);
    ReadUserDto updateUser(Long id, CreateUserDto createUserDto);
    void deleteUser(Long id);
    Page<ReadUserDto> findAll(Pageable pageable);
}
