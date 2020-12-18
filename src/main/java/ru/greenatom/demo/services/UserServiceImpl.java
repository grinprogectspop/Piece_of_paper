package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.models.binding.UserBuildingModel;
import ru.greenatom.demo.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepo userRepo;

    public UserServiceImpl(ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.userRepo = userRepo;
    }

    @Override
    public Long create(UserBuildingModel userBuildingModel) {
        User userEntity = this.modelMapper.map(userBuildingModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);


        this.userRepo.save(userEntity);
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
