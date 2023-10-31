package org.samodumkina.l0;

import jakarta.validation.constraints.NotNull;
import org.samodumkina.dto.UserRequestDto;

public record UserRequest(@NotNull UserRequestDto userRequestDto, @NotNull UserRequestType type) {

}
