package org.samodumkina.controller.l1;

import java.util.List;
import java.util.Objects;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;
import org.samodumkina.service.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/l1/api/users")
public class UserControllerL1 {

  public static final String NO_CONTENT = "No content";
  private final UserServiceImpl userService;

  public UserControllerL1(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUsers() {
    List<UserResponseDto> users = userService.getUsers();
    if (!CollectionUtils.isEmpty(users)) {
      return ResponseEntity.ok(users);
    } else {
      return ResponseEntity.ok(NO_CONTENT);
    }
  }

  @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    UserResponseDto user = userService.getUser(id);
    return ResponseEntity.ok(Objects.requireNonNullElse(user, NO_CONTENT));
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createOrUpdateUser(@RequestBody UserRequestDto userRequestDto) {
    UserResponseDto user = userService.createOrUpdateUser(userRequestDto);
    return ResponseEntity.ok(Objects.requireNonNullElse(user, NO_CONTENT));
  }

  @PostMapping(value = "/{id}:delete", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(NO_CONTENT);
  }
}
