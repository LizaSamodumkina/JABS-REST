package org.samodumkina.mapper;

import java.time.format.DateTimeFormatter;
import org.samodumkina.dto.SubscriptionResponseDto;
import org.samodumkina.model.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionResponseConverter implements Converter<Subscription, SubscriptionResponseDto> {

  @Override
  public SubscriptionResponseDto convert(Subscription source) {
    String startDate = DateTimeFormatter.ISO_LOCAL_DATE.format(source.getStartDate());

    return new SubscriptionResponseDto(source.getId(), source.getUser().getId(), startDate);
  }
}
