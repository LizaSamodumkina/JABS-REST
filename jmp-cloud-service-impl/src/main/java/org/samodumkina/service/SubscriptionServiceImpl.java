package org.samodumkina.service;

import java.time.LocalDate;
import java.util.List;
import org.samodumkina.dao.SubscriptionRepository;
import org.samodumkina.dao.UserRepository;
import org.samodumkina.dto.SubscriptionRequestDto;
import org.samodumkina.dto.SubscriptionResponseDto;
import org.samodumkina.mapper.SubscriptionResponseConverter;
import org.samodumkina.model.Subscription;
import org.samodumkina.model.User;
import org.samodumkina.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final UserRepository userRepository;
  private final SubscriptionResponseConverter subscriptionResponseConverter;

  public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository,
      SubscriptionResponseConverter subscriptionResponseConverter) {
    this.subscriptionRepository = subscriptionRepository;
    this.userRepository = userRepository;
    this.subscriptionResponseConverter = subscriptionResponseConverter;
  }

  @Override
  public SubscriptionResponseDto getSubscription(Long id) {
    Subscription subscription = subscriptionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Subscription with id='%s' does not exist".formatted(id)));

    return subscriptionResponseConverter.convert(subscription);
  }

  @Override
  public List<SubscriptionResponseDto> getAllSubscription() {
    return subscriptionRepository.findAll().stream()
        .map(subscriptionResponseConverter::convert)
        .toList();
  }

  @Transactional
  @Override
  public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
    User user = userRepository.findById(subscriptionRequestDto.userId()).orElseThrow(
        () -> new NotFoundException("There is no employee with id='%s'".formatted(subscriptionRequestDto.userId())));

    Subscription subscription = new Subscription();
    subscription.setUser(user);
    subscription.setStartDate(LocalDate.now());

    Subscription saved = subscriptionRepository.save(subscription);

    return subscriptionResponseConverter.convert(saved);
  }

  @Transactional
  @Override
  public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
    Subscription subscription = subscriptionRepository.findById(subscriptionRequestDto.id()).orElseThrow(() ->
        new NotFoundException("Subscription with id='%s' does not exist".formatted(subscriptionRequestDto.id())));

    User user = userRepository.findById(subscriptionRequestDto.userId()).orElseThrow(
        () -> new NotFoundException("There is no employee with id='%s'".formatted(subscriptionRequestDto.userId())));

    subscription.setUser(user);

    Subscription saved = subscriptionRepository.save(subscription);

    return subscriptionResponseConverter.convert(saved);
  }

  @Transactional
  @Override
  public void deleteSubscription(Long id) {
    subscriptionRepository.deleteById(id);
  }
}
