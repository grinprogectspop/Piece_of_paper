package ru.greenatom.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.greenatom.demo.models.binding.UserBuildingModel;

public interface UserService extends UserDetailsService {
    Long create(UserBuildingModel userBuildingModel);
}
