package org.samodumkina.mapper;

import java.time.LocalDate;
import org.samodumkina.dto.UserRequestDto;
import org.samodumkina.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserRequestDto, User> {

  @Override
  public User convert(UserRequestDto source) {
    User user = new User();

    user.setId(source.getId());
    user.setName(source.getName());
    user.setSurname(source.getSurname());
    user.setBirthday(LocalDate.parse(source.getBirthday()));

    return user;
  }
}
