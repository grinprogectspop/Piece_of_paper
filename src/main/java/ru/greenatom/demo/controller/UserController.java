package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.domain.Views;
import ru.greenatom.demo.models.binding.UserBuildingModel;
import ru.greenatom.demo.repo.UserRepo;
import ru.greenatom.demo.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/registration")
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
     * @param userBuildingModel входной класс (Тело запроса)
     * @param bindingResult проверяет documentBuildingCreateModel на корректность(хранит в себе ошибки при сборке и собран ли он )
     **/
    @PostMapping()
    @ResponseBody
    @JsonView(Views.documents.class)
    public Map<String, Object> crate(@RequestBody @Valid UserBuildingModel userBuildingModel,
                                     BindingResult bindingResult) {
        Map<String, Object> strings = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            bindingResult.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            strings.put("error", errors);
            return strings;
        } else {
            UserBuildingModel userServiceModel = this.modelMapper.map(userBuildingModel, UserBuildingModel.class);
            strings.put("id", this.userService.create(userServiceModel));
            return strings;
        }
    }
}
