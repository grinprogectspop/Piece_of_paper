package ru.greenatom.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Position;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.domain.dto.UserDto;
import ru.greenatom.demo.repo.PositionRepo;
import ru.greenatom.demo.repo.UserRepo;

import java.util.Collections;

/**
 * Сервис для работы с пользователем
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
  public Long create(UserDto userDto) {
    User userFromDb = userRepo.findByEmail(userDto.getEmail());

    if (userFromDb != null) {
      // This means that user is already exists
      return -1L;
    }

    User userEntity = modelMapper.map(userDto, User.class);

    userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
    userEntity.setAccountNonExpired(true);
    userEntity.setAccountNonLocked(true);
    userEntity.setCredentialsNonExpired(true);
    userEntity.setEnabled(true);

    // Just fot testing
    Position position = new Position();
    position.setPositionName("test");

    userRepo.save(userEntity);
    position.setUsers(Collections.singleton(userRepo.findByUserId(userEntity.getUserId())));
    positionRepo.save(position);

    return userEntity.getUserId();
  }

    /**
     * @param email - to log in as username we use E-mail instead of login
     * @return Requested user
     * @throws UsernameNotFoundException - if username doesn't found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Current username doesn't exist!");
        }

        return user;
    }
}
