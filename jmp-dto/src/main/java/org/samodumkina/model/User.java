package org.samodumkina.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String surname;
  private LocalDate birthday;

  public User() {
  }

  public User(Long id, String name, LocalDate birthday) {
    this.id = id;
    this.name = name;
    this.birthday = birthday;
  }

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

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
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
    var that = (User) obj;
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
    return "User[" +
        "id=" + id + ", " +
        "name=" + name + ", " +
        "surname=" + surname + ", " +
        "birthday=" + birthday + ']';
  }
}
