package org.samodumkina.dto;

import jakarta.validation.constraints.NotNull;

public record SubscriptionRequestDto(Long id, @NotNull Long userId) {

}
