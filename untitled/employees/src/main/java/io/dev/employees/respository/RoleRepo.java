package io.dev.employees.respository;

import io.dev.employees.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

/**
 * Created by TuNguyen
 * In 12/13/2021
 */
@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(String name);
}
