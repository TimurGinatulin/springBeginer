package ru.hw.hw.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hw.hw.configuration.jwt.JwtProvider;
import ru.hw.hw.models.dtos.AuthResponseDto;
import ru.hw.hw.models.dtos.SignUpRequestDto;
import ru.hw.hw.models.entitys.UserEntity;
import ru.hw.hw.service.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public UserEntity signUpUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpRequestDto.getUsername());
        userEntity.setPassword(signUpRequestDto.getPassword());
        return service.saveUser(userEntity);
    }

    @PostMapping("/login")
    public AuthResponseDto auth(@RequestBody SignUpRequestDto authResponseDto) {
        UserEntity user = service.findByUserNameAndPassword(authResponseDto.getUsername(), authResponseDto.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }
}
