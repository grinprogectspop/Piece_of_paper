package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
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
}
