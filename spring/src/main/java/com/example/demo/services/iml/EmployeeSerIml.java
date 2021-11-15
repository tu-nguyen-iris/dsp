package com.example.demo.services.iml;

import com.example.demo.Entity.Departments;
import com.example.demo.Entity.Employees;
import com.example.demo.Entity.Jobs;
import com.example.demo.config.ResponseModified;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mapper.EmployeeMaper;
import com.example.demo.respository.DepartmentsRepo;
import com.example.demo.respository.EmployeeRepo;
import com.example.demo.respository.JobsRepo;
import com.example.demo.services.EmployeeSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class EmployeeSerIml implements EmployeeSevice {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JobsRepo jobsRepo;

    @Autowired
    private DepartmentsRepo department;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param response
     * @param employee
     * @param tp
     * @return
     */
    private Map<String, Object> _getStringObjectMap(Map<String, Object> response, List<EmployeeDto> employee, Page<Employees> tp) {
        for (Employees employees : tp) {
            employee.add(EmployeeMaper.toEmployeeDto(employees));
        }
        response.put("data", employee);
        response.put("currentPage", tp.getNumber());
        response.put("totalItems", tp.getTotalElements());
        response.put("totalPages", tp.getTotalPages());
        return response;
    }


    public Map<String, Object> findAllEmployeeByName(Pageable page, String name) {
        Map<String, Object> response = new HashMap<>();
        List<EmployeeDto> employee = new ArrayList<>();
        Page<Employees> tp = employeeRepo.getAllWithName(page, name);
        return _getStringObjectMap(response, employee, tp);
    }

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        List<EmployeeDto> employee = new ArrayList<>();
        Page<Employees> tp = employeeRepo.findAll(pageable);
        return _getStringObjectMap(response, employee, tp);
    }

    @Override
    public boolean deleteByEmployeeId(Long Id) {
        try {
            Optional<Employees> employees = employeeRepo.findById(Id);
            List<Employees> employeesList = employeeRepo.findEmployeesByManagerId(Id);
            if (employees.isPresent() && employeesList.size() == 0) {
                employeeRepo.deleteEmployees(Id);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public EmployeeDto getDetailEmployees(Long id) {
        try {
            Employees employees;
            employees = employeeRepo.getEmployeesByEmployeeId(id);
            if (employees != null) {
                return EmployeeMaper.toEmployeeDto(employees);
            }
            return null;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseModified addAnEmployee(EmployeeDto employeeDto) {
        try {
            //save, persist, update, merge, saveOrUpdate ?
            // that is insert data into database
            if (employeeDto.getEmployeeId() == null) {
                Employees employees = modelMapper.map(employeeDto, Employees.class);
                employees.setJobId(jobsRepo.getById(employeeDto.getJobId()));
                employees.setDepartmentId(department.getById(employeeDto.getDepartmentId()));
                employeeRepo.save(employees);
                return new ResponseModified(1, "SUCCESS", null);
            } else {
                if (employeeRepo.existsById(employeeDto.getEmployeeId())) {
                    Employees employees = employeeRepo.getEmployeesByEmployeeId(employeeDto.getEmployeeId());
                    employees.setJobId(jobsRepo.getById(employeeDto.getJobId()));
                    employees.setDepartmentId(department.getById(employeeDto.getDepartmentId()));
                    employees.setFirstName(employeeDto.getFirstName());
                    employees.setLastName(employeeDto.getLastName());
                    employees.setSalary(employeeDto.getSalary());
                    employees.setEmail(employeeDto.getEmail());
                    employees.setHireDate(employeeDto.getHireDate());
                    employeeRepo.save(employees);
                    return new ResponseModified(1, "SUCCESS", null);
                }
                return new ResponseModified(0, "EMPLOYEE DO NOT EXIST", null);
            }
        } catch (DataAccessException e) {
            return new ResponseModified(0, "ERROR", null);
        }
    }


}
