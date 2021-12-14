package io.dev.gateway.entity;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String roleName;
}
