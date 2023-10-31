package org.samodumkina.service;

import java.util.List;
import org.samodumkina.dto.SubscriptionRequestDto;
import org.samodumkina.dto.SubscriptionResponseDto;

public interface SubscriptionService {

  SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

  SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

  void deleteSubscription(Long id);

  SubscriptionResponseDto getSubscription(Long id);

  List<SubscriptionResponseDto> getAllSubscription();
}
