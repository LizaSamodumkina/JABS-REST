package org.samodumkina.service;

import java.time.LocalDate;
import java.util.List;
import org.samodumkina.dao.UserRepository;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;
import org.samodumkina.mapper.UserConverter;
import org.samodumkina.mapper.UserResponseConverter;
import org.samodumkina.model.User;
import org.samodumkina.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserConverter userConverter;
  private final UserResponseConverter userResponseConverter;

  public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
      UserResponseConverter userResponseConverter) {
    this.userRepository = userRepository;
    this.userConverter = userConverter;
    this.userResponseConverter = userResponseConverter;
  }

  @Override
  public UserResponseDto getUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("There is no employee with id='%s'".formatted(id)));

    return userResponseConverter.convert(user);
  }

  @Override
  public List<UserResponseDto> getUsers() {
    return userRepository.findAll().stream()
        .map(userResponseConverter::convert)
        .toList();
  }

  @Transactional
  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = userConverter.convert(userRequestDto);

    return userResponseConverter.convert(userRepository.save(user));
  }

  @Transactional
  @Override
  public UserResponseDto updateUser(UserRequestDto userRequestDto) {
    User user = userRepository.findById(userRequestDto.getId()).orElseThrow(
        () -> new NotFoundException("There is no employee with id='%s'".formatted(userRequestDto.getId())));

    user.setId(userRequestDto.getId());
    user.setName(userRequestDto.getName());
    user.setSurname(userRequestDto.getSurname());
    User saved = userRepository.save(user);

    return userResponseConverter.convert(saved);
  }

  @Transactional
  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public UserResponseDto createOrUpdateUser(UserRequestDto userRequestDto) {
    User user = userRepository.findById(userRequestDto.getId()).orElse(new User());

    user.setName(userRequestDto.getName());
    user.setSurname(userRequestDto.getSurname());
    user.setBirthday(LocalDate.parse(userRequestDto.getBirthday()));

    User saved = userRepository.save(user);

    return userResponseConverter.convert(saved);
  }
}
