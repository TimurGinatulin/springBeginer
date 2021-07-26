package ru.hw.hw.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hw.hw.models.dtos.ResponseDto;
import ru.hw.hw.models.entitys.UserEntity;
import ru.hw.hw.service.UserService;


@RestController
@RequestMapping("/app/score")
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping("/inc")
    public UserEntity incScore(@RequestHeader("Authorization") String authorization) {
        UserEntity userByToken = service.findUserByToken(authorization.substring(7));
        return service.incScore(userByToken.getUsername());
    }

    @GetMapping("/dec")
    public UserEntity decScore(@RequestHeader("Authorization") String authorization) {
        UserEntity userByToken = service.findUserByToken(authorization.substring(7));
        return service.decScore(userByToken.getUsername());
    }

    @GetMapping("/get/current")
    public ResponseDto getCurrentScore(@RequestHeader("Authorization") String authorization) {
        UserEntity userByToken = service.findUserByToken(authorization.substring(7));
        return service.getScoreByUsername(userByToken.getUsername());
    }

    @GetMapping("/get/{id}")
    public ResponseDto getScoreById(@PathVariable Integer id) {
        return service.getScoreById(id);
    }
}
