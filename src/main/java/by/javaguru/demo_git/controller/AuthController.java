package by.javaguru.demo_git.controller;

import by.javaguru.demo_git.dto.UserDto;
import by.javaguru.demo_git.entity.UserEntity;
import by.javaguru.demo_git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) throws Exception {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is already in use", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .password(encodedPassword)
                .role(userDto.getRole())
                .build();
        userRepository.save(userEntity);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
}
