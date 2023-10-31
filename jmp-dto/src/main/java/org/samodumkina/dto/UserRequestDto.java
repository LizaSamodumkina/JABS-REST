package org.samodumkina.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

public class UserRequestDto {

  private Long id;
  @NotBlank
  private String name;
  @NotBlank
  private String surname;

  private String birthday;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (UserRequestDto) obj;
    return Objects.equals(this.id, that.id) &&
        Objects.equals(this.name, that.name) &&
        Objects.equals(this.surname, that.surname) &&
        Objects.equals(this.birthday, that.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, birthday);
  }

  @Override
  public String toString() {
    return "UserRequestDto[" +
        "id=" + id + ", " +
        "name=" + name + ", " +
        "surname=" + surname + ", " +
        "birthday=" + birthday + ']';
  }
}
