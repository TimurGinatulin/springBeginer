package ru.hw.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.models.dtos.ResponseDto;
import ru.hw.hw.models.entitys.RoleEntity;
import ru.hw.hw.models.entitys.UserEntity;
import ru.hw.hw.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRole())).collect(Collectors.toList());
    }

    public UserEntity incScore(String name) {
        UserEntity user = findByUsername(name)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setScore(user.getScore() + 1);
        return repository.save(user);
    }

    public UserEntity decScore(String name) {
        UserEntity user = findByUsername(name)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setScore(user.getScore() - 1);
        return repository.save(user);
    }

    public ResponseDto getScoreByUsername(String name) {
        return new ResponseDto(HttpStatus.I_AM_A_TEAPOT.value(), String.valueOf(
                findByUsername(name)
                        .orElseThrow(() -> new NotFoundException("User not found"))
                        .getScore()));
    }

    public ResponseDto getScoreById(Integer id) {
        return getScoreByUsername(
                repository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found"))
                        .getUsername());
    }
}
