package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.controller.utils.ControllerUtils;
import ru.greenatom.demo.domain.Views;
import ru.greenatom.demo.domain.dto.UserDto;
import ru.greenatom.demo.repo.UserRepo;
import ru.greenatom.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserRepo userRepo, UserService userService, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * @param userDto - входной класс (Тело запроса)
     * @param passwordConfirm - повторный ввод пароля
     * @param bindingResult - проверяет documentBuildingCreateModel на корректность
     *                      (хранит в себе ошибки при сборке и собран ли он)
     **/
    @PostMapping("/registration")
    @ResponseBody
    @JsonView(Views.documents.class)
    public String createUser(
            @RequestBody @Valid UserDto userDto,
            @RequestParam("passwordConfirm") String passwordConfirm,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isPasswordsEquals = userDto.getPassword().equals(passwordConfirm);
        boolean isConfirmEmpty = passwordConfirm.isEmpty();

        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Password confirmation can't be empty!");
        }

        if (userDto.getPassword() != null && !isPasswordsEquals) {
            model.addAttribute("passwordError", "Passwords are not equal!");
        }

        if (isConfirmEmpty || !isPasswordsEquals || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        model.addAttribute("id", this.userService.create(userDto));

        return "login";
    }
}
