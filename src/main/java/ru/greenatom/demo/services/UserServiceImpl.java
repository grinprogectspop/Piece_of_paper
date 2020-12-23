package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Position;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.models.binding.UserBuildingModel;
import ru.greenatom.demo.repo.PositionRepo;
import ru.greenatom.demo.repo.UserRepo;

import java.util.Collections;
/**
 * сервер для работы с пользователем
 * **/
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepo userRepo;
    private final PositionRepo positionRepo;

    public UserServiceImpl(ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepo userRepo, PositionRepo positionRepo) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.userRepo = userRepo;
        this.positionRepo = positionRepo;
    }

    @Override
    public Long create(UserBuildingModel userBuildingModel) {
        User userEntity = this.modelMapper.map(userBuildingModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);

        Position position = new Position();
        position.setPositionName("test");

        this.userRepo.save(userEntity);
        position.setUsers(Collections.singleton(userRepo.findOneByUserId(userEntity.getUserId())));
        positionRepo.save(position);
        return userEntity.getUserId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findOneByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }
}
