package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.validation.IsEmailRegistered;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MessageDto {
    @Email(message = "Неправильно указан E-mail")
    @IsEmailRegistered
    private String email;

    @NotBlank
    private Long documentId;

    @NotBlank
    private Long userId;

    @NotBlank
    private String message;
}
