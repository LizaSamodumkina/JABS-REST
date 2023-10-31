package org.samodumkina.mapper;

import java.time.format.DateTimeFormatter;
import org.samodumkina.dto.UserResponseDto;
import org.samodumkina.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<User, UserResponseDto> {

  @Override
  public UserResponseDto convert(User source) {
    UserResponseDto userResponseDto = new UserResponseDto();

    userResponseDto.setId(source.getId());
    userResponseDto.setName(source.getName());
    userResponseDto.setSurname(source.getSurname());
    userResponseDto.setBirthday(DateTimeFormatter.ISO_LOCAL_DATE.format(source.getBirthday()));

    return userResponseDto;
  }
}
