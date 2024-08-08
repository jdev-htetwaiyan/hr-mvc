package com.jdev.hr_mvc.services;

import com.jdev.hr_mvc.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getById(long id);

    Employee update(Employee employee, long id);

    void delete(long id);

    void assignEmployeeToDepartment(long departmentId, List<Long> employeeIds);

    void assignPositionToEmployee(long positionId, List<Long> employeeIds);

}
