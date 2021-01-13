package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.validation.IsEmailRegistered;
import ru.greenatom.demo.validation.IsPasswordMatching;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@IsPasswordMatching
public class UserDto {
    @Email(message = "Неправильно указан E-mail")
    @IsEmailRegistered
    private String email;

    @Size(min = 4, max = 20, message = "Имя пользователя должно быть от 4 до 20 символов")
    @NotBlank
    private String userName;

    @Size(min = 4, max = 20, message = "Фамилия пользователя должно быть от 4 до 20 символов")
    @NotBlank
    private String surname;

    @NotBlank
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;

    @NotBlank
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;
}
