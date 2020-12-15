package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  private String patronymic;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @ElementCollection(targetClass = AccessType.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "access", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<AccessType> accessTypes;

  @ManyToMany
  @JoinTable(
    name = "user_position",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "position_id")
  )
  private Set<Position> positions;


}
