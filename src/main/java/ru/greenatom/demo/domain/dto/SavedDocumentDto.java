package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.domain.DocumentVersion;
import ru.greenatom.demo.validation.IsPasswordMatching;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@IsPasswordMatching
public class SavedDocumentDto {
    @NotBlank
    private Long userId;

    @NotBlank
    private Long documentId;

    private DocumentVersion versionEdit;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;

    private String description;
}