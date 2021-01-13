package ru.greenatom.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.greenatom.demo.domain.dto.UserDto;

public interface UserService extends UserDetailsService {
    Long create(UserDto userDto);
}