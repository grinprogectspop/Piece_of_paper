package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.domain.DocumentType;
import ru.greenatom.demo.domain.SecrecyLevel;
import ru.greenatom.demo.validation.IsPasswordMatching;

import javax.validation.constraints.Size;


@Data
@IsPasswordMatching
public class CreatedDocumentDto {
    @Size(min = 4, max = 100, message = "Имя документа должно быть от 4 до 100 символов")
    private String documentName;

    private SecrecyLevel secrecyLevel;

    private DocumentType documentType;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;
}


