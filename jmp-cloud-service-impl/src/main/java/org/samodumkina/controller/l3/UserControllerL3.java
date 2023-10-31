package org.samodumkina.controller.l3;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.dto.UserResponseDto;
import org.samodumkina.l3.UserResponseDtoL3;
import org.samodumkina.service.UserServiceImpl;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/l3/api/users")
public class UserControllerL3 {

  private final UserServiceImpl userService;

  public UserControllerL3(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUsers() {
    List<UserResponseDto> users = userService.getUsers();
    if (!CollectionUtils.isEmpty(users)) {
      List<UserResponseDtoL3> hateoasUsers = new ArrayList<>(users.size());
      for (UserResponseDto dto : users) {
        UserResponseDtoL3 userResponseDtoL3 = new UserResponseDtoL3(dto.getId(), dto.getName(), dto.getSurname(),
            dto.getBirthday());
        userResponseDtoL3.add(Link.of(buildURI(dto.getId()).toString()));
        hateoasUsers.add(userResponseDtoL3);
      }
      return ResponseEntity.ok(hateoasUsers);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    UserResponseDto user = userService.getUser(id);
    return returnSingleDto(user);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
    UserResponseDto user = userService.createUser(userRequestDto);
    return returnSingleDto(user);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
    if (!id.equals(userRequestDto.getId())) {
      return ResponseEntity.badRequest().build();
    }

    UserResponseDto userResponseDto = userService.updateUser(userRequestDto);
    return returnSingleDto(userResponseDto);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  private ResponseEntity<?> returnSingleDto(UserResponseDto user) {
    if (user != null) {
      UserResponseDtoL3 userl3 = new UserResponseDtoL3(user.getId(), user.getName(), user.getSurname(),
          user.getBirthday());
      userl3.add(Link.of(buildURI(user.getId()).toString()));
      return ResponseEntity.ok(userl3);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  private URI buildURI(Long id) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{id}")
        .buildAndExpand(id)
        .toUri();
  }
}
