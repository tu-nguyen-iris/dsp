package io.dev.gateway.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Entity
@Table(name="roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "rolesList")
    private List<User> userList;
}
