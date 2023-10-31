package org.samodumkina.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;
import org.samodumkina.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserControllerImpl implements UserController {

  private final UserServiceImpl userService;

  public UserControllerImpl(UserServiceImpl userService) {
    this.userService = userService;
  }

  @Override
  @GetMapping(value = "/{id}")
  public UserResponseDto getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @Override
  @GetMapping
  public List<UserResponseDto> getUsers() {
    return userService.getUsers();
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
    return userService.createUser(userRequestDto);
  }

  @Override
  @PutMapping
  public UserResponseDto updateUser(@RequestBody @Valid UserRequestDto userRequestDto) {
    return userService.updateUser(userRequestDto);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
