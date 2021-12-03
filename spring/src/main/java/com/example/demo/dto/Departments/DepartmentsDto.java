package com.example.demo.dto.Departments;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentsDto {
    private Long id;
    private String departmentName;
    private Long locationId;
}
