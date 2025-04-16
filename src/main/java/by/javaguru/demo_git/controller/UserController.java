package by.javaguru.demo_git.controller;

import by.javaguru.demo_git.dto.UserDto;
import by.javaguru.demo_git.entity.UserEntity;
import by.javaguru.demo_git.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public UserEntity save(@RequestBody UserDto userDto) {
       return userService.save(userDto);
    }

}
