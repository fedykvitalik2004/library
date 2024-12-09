package vitaliifedyk.library.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vitaliifedyk.library.dto.CreateUserDto;
import vitaliifedyk.library.dto.ReadUserDto;
import vitaliifedyk.library.exception.EmailAlreadyUsed;
import vitaliifedyk.library.exception.NotFoundException;
import vitaliifedyk.library.exception.OperationNotPermittedException;
import vitaliifedyk.library.mapper.UserMapper;
import vitaliifedyk.library.model.User;
import vitaliifedyk.library.repository.UserRepository;
import vitaliifedyk.library.service.BorrowedBookService;
import vitaliifedyk.library.service.UserService;

import static vitaliifedyk.library.constant.ExceptionConstants.*;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;
    private BorrowedBookService bookServiceImpl;

    @Override
    public ReadUserDto createUser(final CreateUserDto createUserDto) {
        if (userRepository.exists(Example.of(new User().setEmail(createUserDto.getEmail())))) {
            throw new EmailAlreadyUsed(EMAIL_ALREADY_USED.formatted(createUserDto.getEmail()));
        }
        final User user = userMapper.toUser(createUserDto);
        return userMapper.toReadUserDto(userRepository.save(user));
    }

    @Override
    public ReadUserDto readUser(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_ID.formatted(id)));
        return userMapper.toReadUserDto(user);
    }

    @Override
    public ReadUserDto updateUser(final Long id, final CreateUserDto createUserDto) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_ID.formatted(id)));
        if (userRepository.existsByEmailAndIdNot(createUserDto.getEmail(), id)) {
            throw new EmailAlreadyUsed(EMAIL_ALREADY_USED.formatted(createUserDto.getEmail()));
        }
        userMapper.updateUserFromCreateUserDto(createUserDto, user);
        return userMapper.toReadUserDto(user);
    }

    @Override
    public void deleteUser(final Long id) {
        if (bookServiceImpl.isBorrowedByUser(id)) {
            throw new OperationNotPermittedException(USER_CANNOT_BE_DELETED);
        }
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_ID.formatted(id)));
        userRepository.delete(user);
    }

    @Override
    public Page<ReadUserDto> findAll(final Pageable pageable) {
        return userRepository.findAll(pageable).map(readUserDto -> userMapper.toReadUserDto(readUserDto));
    }
}
