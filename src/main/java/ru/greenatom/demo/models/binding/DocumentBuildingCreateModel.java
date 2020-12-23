package ru.greenatom.demo.models.binding;

import lombok.Data;
import ru.greenatom.demo.domain.DocumentType;
import ru.greenatom.demo.domain.SecrecyLevel;
import ru.greenatom.demo.models.binding.validations.IsPasswordMatching;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@IsPasswordMatching
public class DocumentBuildingCreateModel {

    @NotNull
    private long userId;

    @Size(min = 4, max = 100, message = "Имя документа должно быть от 4 до 100 символов")
    private String nameDocument;
    private SecrecyLevel secrecyLevel;
    private DocumentType documentType;

    //Todo сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;

    //Todo сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;
}


