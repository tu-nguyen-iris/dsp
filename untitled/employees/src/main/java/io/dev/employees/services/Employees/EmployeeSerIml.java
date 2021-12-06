package io.dev.employees.services.Employees;

import io.dev.employees.Entity.Employees;
import io.dev.employees.config.ResponseModified;
import io.dev.employees.dto.employee.EmployeeDto;
import io.dev.employees.dto.employee.EmployeeDtoDetail;
import io.dev.employees.mapper.EmployeeMaper;
import io.dev.employees.respository.DepartmentsRepo;
import io.dev.employees.respository.EmployeeRepo;
import io.dev.employees.respository.JobsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeSerIml implements EmployeeSevices {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JobsRepo jobsRepo;

    @Autowired
    private DepartmentsRepo department;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeMaper employeeMaper;

    /**
     * @param response
     * @param employee
     * @param tp
     * @return
     */
    private Map<String, Object> _getStringObjectMap(Map<String, Object> response, List<EmployeeDtoDetail> employee, Page<Employees> tp) {
        for (Employees employees : tp) {
            employee.add(employeeMaper.toEmployeeDetailDto(employees));
        }
        response.put("data", employee);
        response.put("currentPage", tp.getNumber());
        response.put("totalItems", tp.getTotalElements());
        response.put("totalPages", tp.getTotalPages());
        return response;
    }


    public Map<String, Object> findAllEmployeeByName(Pageable page, String name) {
        Map<String, Object> response = new HashMap<>();
        List<EmployeeDtoDetail> employee = new ArrayList<>();
        Page<Employees> tp = employeeRepo.getAllWithName(page, name);
        return _getStringObjectMap(response, employee, tp);
    }

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        List<EmployeeDtoDetail> employee = new ArrayList<>();
        Page<Employees> tp = employeeRepo.findAll(pageable);
        return _getStringObjectMap(response, employee, tp);
    }

    @Override
    public boolean deleteByEmployeeId(Long Id) {
        try {
            Optional<Employees> employees = employeeRepo.findById(Id);
            if (employees.isPresent()) {
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
    public EmployeeDtoDetail getDetailEmployees(Long id) {
        try {
            Employees employees;
            employees = employeeRepo.getEmployeesByEmployeeId(id);
            if (employees != null) {
                return employeeMaper.toEmployeeDetailDto(employees);
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
            Employees employees = modelMapper.map(employeeDto, Employees.class);
            employees.setJobId(jobsRepo.getById(employeeDto.getJobId()));
            employees.setDepartmentId(department.getById(employeeDto.getDepartmentId()));
            employeeRepo.save(employees);
            return new ResponseModified(1, "SUCCESS", null);
        } catch (DataAccessException e) {
            return new ResponseModified(0, "ERROR", null);
        }
    }

    public ResponseModified editEmployee(EmployeeDto employeeDto) {
        try {
            Employees employees = employeeRepo.getEmployeesByEmployeeId(employeeDto.getEmployeeId());
//                    .orElseThrow(() -> new ResponseModified(0, "ERROR", null));
            System.out.println(employees);
            employees.setJobId(jobsRepo.getById(employeeDto.getJobId()));
            employees.setDepartmentId(department.getById(employeeDto.getDepartmentId()));
            employees.setFirstName(employeeDto.getFirstName());
            employees.setLastName(employeeDto.getLastName());
            employees.setSalary(employeeDto.getSalary());
            employees.setEmail(employeeDto.getEmail());
            employees.setHireDate(employeeDto.getHireDate());
            employeeRepo.save(employees);
            return new ResponseModified(1, "SUCCESS", null);
        } catch (DataAccessException  e) {
            return new ResponseModified(0, "ERROR", null);
        }
    }

}
