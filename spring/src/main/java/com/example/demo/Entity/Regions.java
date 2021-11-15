package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "regions")
public class Regions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long regionId;

    @Column
    @Getter
    @Setter
    private String regionName;


}
