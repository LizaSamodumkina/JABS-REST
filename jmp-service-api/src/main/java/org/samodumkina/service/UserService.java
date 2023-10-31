package org.samodumkina.service;

import java.util.List;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;

public interface UserService {

  UserResponseDto getUser(Long id);

  List<UserResponseDto> getUsers();

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto updateUser(UserRequestDto userRequestDto);

  void deleteUser(Long id);
}
