package org.samodumkina.controller.l0;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.samodumkina.l0.UserRequest;
import org.samodumkina.service.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/l0/userService")
public class UserControllerL0 {

  private final UserServiceImpl userService;

  public UserControllerL0(UserServiceImpl userService) {
    this.userService = userService;
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Request is processed"),
      @ApiResponse(responseCode = "200", description = "Internal Server Error")
  })
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> user(@RequestBody @Valid UserRequest userRequest) {
    switch (userRequest.type()) {
      case GET_USER -> {
        return ResponseEntity.ok(userService.getUser(userRequest.userRequestDto().getId()));
      }
      case GET_USERS -> {
        return ResponseEntity.ok(userService.getUsers());
      }
      case CREATE_USER -> {
        return ResponseEntity.ok(userService.createUser(userRequest.userRequestDto()));
      }
      case UPDATE_USER -> {
        return ResponseEntity.ok(userService.updateUser(userRequest.userRequestDto()));
      }
      case DELETE_USER -> {
        userService.deleteUser(userRequest.userRequestDto().getId());
        return ResponseEntity.ok("User is deleted");
      }
      default -> {
        return ResponseEntity.ok("Bad request");
      }
    }
  }
}
