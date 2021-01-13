package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.validation.IsPasswordMatching;

@Data
@IsPasswordMatching
public class ChangeAccessDto {
    Long userToChangeId;

    Long documentId;

    Action action;
}
