package io.dev.gateway.reponsitory;

import io.dev.gateway.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tunguyen on 15/12/2021 9:58 CH
 *
 * @Version 1.0
 * Take it easy.
 */
public interface RoleRepo extends JpaRepository<Roles, Long> {

}
