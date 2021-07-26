package ru.hw.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hw.hw.configuration.CustomUserDetails;
import ru.hw.hw.configuration.jwt.JwtProvider;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.models.dtos.ResponseDto;
import ru.hw.hw.models.entitys.RoleEntity;
import ru.hw.hw.models.entitys.UserEntity;
import ru.hw.hw.models.modelsEnum.UserEnum;
import ru.hw.hw.repository.RoleRepository;
import ru.hw.hw.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public UserEntity findUserByToken(String token) {
        String username = jwtProvider.getLoginFormToken(token);
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserEntity saveUser(UserEntity user) {
        RoleEntity role = roleRepository.findByRole(UserEnum.USER.getRole()).orElseThrow();
        user.setRoles(Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public CustomUserDetails loadByUsername(String username) {
        UserEntity user = findByUsername(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserEntity findByUserNameAndPassword(String username, String password) {
        UserEntity user = findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword()))
            return user;
        }
        return null;
    }

    public UserEntity incScore(String name) {
        UserEntity user = findByUsername(name);
        user.setScore(user.getScore() + 1);
        return userRepository.save(user);
    }

    public UserEntity decScore(String name) {
        UserEntity user = findByUsername(name);
        user.setScore(user.getScore() - 1);
        return userRepository.save(user);
    }

    public ResponseDto getScoreByUsername(String name) {
        return new ResponseDto(HttpStatus.I_AM_A_TEAPOT.value(), String.valueOf(
                findByUsername(name).getScore()));
    }

    public ResponseDto getScoreById(Integer id) {
        return getScoreByUsername(
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found"))
                        .getUsername());
    }
}
