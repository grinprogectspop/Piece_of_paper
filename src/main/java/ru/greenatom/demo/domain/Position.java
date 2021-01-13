package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity

public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long positionId;

  @NotBlank
  private String positionName;

  @ManyToMany
  @JoinTable(
    name = "user_position",
    joinColumns = @JoinColumn(name = "position_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> users;

  public Long getPositionId() {
    return positionId;
  }

  public void setPositionId(Long positionId) {
    this.positionId = positionId;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
