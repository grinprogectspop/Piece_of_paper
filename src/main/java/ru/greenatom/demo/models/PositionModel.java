package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.User;

import java.util.Set;


@Data
public class PositionModel {
  private Long positionId;
  private String positionName;
  private Set<User> users;
}
