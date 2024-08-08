package com.jdev.hr_mvc.services.Impl;

import com.jdev.hr_mvc.Exceptions.EmployeeAlreadyExistsException;
import com.jdev.hr_mvc.Exceptions.ResourceNotFoundException;
import com.jdev.hr_mvc.models.Department;
import com.jdev.hr_mvc.models.Employee;
import com.jdev.hr_mvc.models.Position;
import com.jdev.hr_mvc.repositories.DepartmentRepository;
import com.jdev.hr_mvc.repositories.EmployeeRepository;
import com.jdev.hr_mvc.repositories.PositionRepository;
import com.jdev.hr_mvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository empRepo, DepartmentRepository depRepo,
                               PositionRepository posRepo) {
        this.employeeRepository = empRepo;
        this.departmentRepository = depRepo;
        this.positionRepository = posRepo;
    }

    @Override
    public Employee create(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with email " + employee.getEmail() + " already exists.");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee update(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                                                      .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNo(employee.getPhoneNo());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void assignEmployeeToDepartment(long departmentId, List<Long> employeeIds) {

        Optional<Department> foundDepartment = departmentRepository.findById(departmentId);

        if (foundDepartment.isPresent()) {
            Department department = foundDepartment.get();

            List<Employee> employees = employeeRepository.findAllById(employeeIds);

            // Add into assignedDepartment array
            for (Employee employee : employees) {
                employee.getAssignedDepartment().add(department);
            }

            employeeRepository.saveAll(employees);

            // update department
            department.getEmployeeSet().addAll(employees);
            departmentRepository.save(department);

        } else {
            throw new ResourceNotFoundException("Department with ID " + departmentId + " not found");
        }

    }

    @Override
    public void assignPositionToEmployee(long positionId, List<Long> employeeIds) {
        Optional<Position> foundedPosition = positionRepository.findById(positionId);

        if (foundedPosition.isPresent()) {
            Position position = foundedPosition.get();

            List<Employee> employees = employeeRepository.findAllById(employeeIds);

            // Add into assignedPosition array
            for (Employee employee : employees) {
                employee.getAssignedPosition().add(position);
            }

            employeeRepository.saveAll(employees);

            // update department
            position.getEmployeeSet().addAll(employees);
            positionRepository.save(position);

        } else {
            throw new ResourceNotFoundException("Department with ID " + positionId + " not found");
        }
    }

}

