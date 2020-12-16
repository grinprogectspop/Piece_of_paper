package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.DocumentHistory;
import ru.greenatom.demo.domain.Position;
import ru.greenatom.demo.domain.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UserModel{

    private Long userId;

    private String name;
    private String surname;
    private String patronymic;
    @Email()
    @NotEmpty()
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Action> accessTypes;
    private Set<Position> positions;
    private Set<DocumentHistory> documentsChanges;
}
