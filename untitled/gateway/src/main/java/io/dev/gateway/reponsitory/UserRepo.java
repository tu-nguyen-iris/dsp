package io.dev.gateway.reponsitory;

import io.dev.gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
