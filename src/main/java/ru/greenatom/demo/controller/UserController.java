package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.controller.utils.ControllerUtils;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.domain.Views;
import ru.greenatom.demo.domain.dto.UserDto;
import ru.greenatom.demo.repo.UserRepo;
import ru.greenatom.demo.service.UserService;

import javax.validation.Valid;
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
     * @param bindingResult - проверяет documentBuildingCreateModel на корректность
     *                      (хранит в себе ошибки при сборке и собран ли он)
     **/
    @PostMapping("/registration")
    @ResponseBody
    @JsonView(Views.documents.class)
    public String createUser(
            @RequestBody @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model
    ) {


        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.addAttribute("errors", errors);

            return "registration";
        }

        model.addAttribute("id", this.userService.create(userDto));

        return "login";
    }
    @GetMapping("/123")
    @ResponseBody
    @JsonView(Views.IdName.class)
    public List<User> users(){
        return userRepo.findAll();
    }

}
