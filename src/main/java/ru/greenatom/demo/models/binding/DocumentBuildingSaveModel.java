package ru.greenatom.demo.models.binding;

import lombok.Data;
import ru.greenatom.demo.domain.DocumentVersion;
import ru.greenatom.demo.models.binding.validations.IsPasswordMatching;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@IsPasswordMatching
public class DocumentBuildingSaveModel {
    @NotNull
    private Long userId;
    @NotNull
    private Long idDocument;
    private DocumentVersion versionEdit;

    //Todo сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;
    //Todo сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;

    private String description;

}


