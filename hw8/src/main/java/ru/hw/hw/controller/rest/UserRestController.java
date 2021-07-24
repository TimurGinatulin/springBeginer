package ru.hw.hw.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hw.hw.models.dtos.ResponseDto;
import ru.hw.hw.models.entitys.UserEntity;
import ru.hw.hw.service.AppUserDetailsService;

import java.security.Principal;

@RestController
@RequestMapping("/app/score")
public class UserRestController {
    @Autowired
    private AppUserDetailsService service;

    @GetMapping("/inc")
    public UserEntity incScore(Principal principal) {
        return service.incScore(principal.getName());
    }

    @GetMapping("/dec")
    public UserEntity decScore(Principal principal) {
        return service.decScore(principal.getName());
    }

    @GetMapping("/get/current")
    public ResponseDto getCurrentScore(Principal principal) {
        return service.getScoreByUsername(principal.getName());
    }

    @GetMapping("/get/{id}")
    public ResponseDto getScoreById(@PathVariable Integer id) {
        return service.getScoreById(id);
    }
}
