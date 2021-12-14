package io.dev.employees.respository;

import io.dev.employees.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by TuNguyen
 * In 12/13/2021
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String username);

    Boolean existsByEmail(String email);
}
