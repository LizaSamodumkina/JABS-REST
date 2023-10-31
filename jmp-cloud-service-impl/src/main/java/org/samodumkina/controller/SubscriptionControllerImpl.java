package org.samodumkina.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.samodumkina.dto.SubscriptionRequestDto;
import org.samodumkina.dto.SubscriptionResponseDto;
import org.samodumkina.service.SubscriptionServiceImpl;
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
@RequestMapping(value = "/api/subscriptions")
public class SubscriptionControllerImpl implements SubscriptionController {

  private final SubscriptionServiceImpl service;

  public SubscriptionControllerImpl(SubscriptionServiceImpl service) {
    this.service = service;
  }

  @Override
  @GetMapping(value = "/{id}")
  public SubscriptionResponseDto getSubscription(@PathVariable Long id) {
    return service.getSubscription(id);
  }

  @Override
  @GetMapping
  public List<SubscriptionResponseDto> getAllSubscription() {
    return service.getAllSubscription();
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SubscriptionResponseDto createSubscription(@RequestBody @Valid SubscriptionRequestDto subscriptionRequestDto) {
    return service.createSubscription(subscriptionRequestDto);
  }

  @Override
  @PutMapping
  public SubscriptionResponseDto updateSubscription(@RequestBody @Valid SubscriptionRequestDto subscriptionRequestDto) {
    return service.updateSubscription(subscriptionRequestDto);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSubscription(@PathVariable Long id) {
    service.deleteSubscription(id);
  }
}
