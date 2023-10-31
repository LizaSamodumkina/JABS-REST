package org.samodumkina.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "subscription")
public class Subscription {

  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  private LocalDate startDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (Subscription) obj;
    return Objects.equals(this.id, that.id) &&
        Objects.equals(this.user, that.user) &&
        Objects.equals(this.startDate, that.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, startDate);
  }

  @Override
  public String toString() {
    return "Subscription[" +
        "id=" + id + ", " +
        "user=" + user + ", " +
        "startDate=" + startDate + ']';
  }

}
