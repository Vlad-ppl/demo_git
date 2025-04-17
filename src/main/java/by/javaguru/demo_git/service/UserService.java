package by.javaguru.demo_git.service;

import by.javaguru.demo_git.dto.UserDto;
import by.javaguru.demo_git.entity.UserEntity;
import by.javaguru.demo_git.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity save(@NotNull UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .build();
        return userRepository.save(userEntity);
    }
}
