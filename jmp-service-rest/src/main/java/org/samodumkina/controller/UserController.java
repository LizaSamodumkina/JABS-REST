package org.samodumkina.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;

public interface UserController {

  UserResponseDto getUser(Long id);

  List<UserResponseDto> getUsers();

  UserResponseDto createUser(@Valid UserRequestDto userRequestDto);

  UserResponseDto updateUser(@Valid UserRequestDto userRequestDto);

  void deleteUser(Long id);
}
