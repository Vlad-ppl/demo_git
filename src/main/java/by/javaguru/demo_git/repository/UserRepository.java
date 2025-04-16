package by.javaguru.demo_git.repository;

import by.javaguru.demo_git.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Integer, UserEntity> {
    Optional<UserEntity> findById(int id);
}
