package org.samodumkina.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.samodumkina.dto.SubscriptionRequestDto;
import org.samodumkina.dto.SubscriptionResponseDto;

public interface SubscriptionController {

  SubscriptionResponseDto createSubscription(@Valid SubscriptionRequestDto subscriptionRequestDto);

  SubscriptionResponseDto updateSubscription(@Valid SubscriptionRequestDto subscriptionRequestDto);

  void deleteSubscription(Long id);

  SubscriptionResponseDto getSubscription(Long id);

  List<SubscriptionResponseDto> getAllSubscription();

}
