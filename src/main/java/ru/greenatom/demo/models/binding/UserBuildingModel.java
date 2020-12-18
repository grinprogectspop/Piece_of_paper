package ru.greenatom.demo.models.binding;

import lombok.Data;
import ru.greenatom.demo.models.binding.validations.IsEmailRegistered;
import ru.greenatom.demo.models.binding.validations.IsPasswordMatching;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@IsPasswordMatching
public class UserBuildingModel {
    @Email(message = "Неправильно указан Email")
    @IsEmailRegistered

    private String email;
    @Size(min = 4, max = 20, message = "Имя пользователя должно быть от 4 до 20 символов")
    @NotNull
    private String userName;
    @Size(min = 4, max = 20, message = "Фамилия пользователя должно быть от 4 до 20 символов")
    @NotNull
    private String surname;
    @NotNull
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;
    @NotNull
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;


}
