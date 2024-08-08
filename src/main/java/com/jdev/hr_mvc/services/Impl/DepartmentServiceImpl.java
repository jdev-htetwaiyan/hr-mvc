package com.jdev.hr_mvc.services.Impl;

import com.jdev.hr_mvc.Exceptions.DepartmentAlreadyExistsException;
import com.jdev.hr_mvc.Exceptions.ResourceNotFoundException;
import com.jdev.hr_mvc.models.Department;
import com.jdev.hr_mvc.models.Employee;
import com.jdev.hr_mvc.repositories.DepartmentRepository;
import com.jdev.hr_mvc.repositories.EmployeeRepository;
import com.jdev.hr_mvc.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository depRepo, EmployeeRepository empRepo) {

        this.departmentRepository = depRepo;
        this.employeeRepository = empRepo;
    }

    @Override
    public Department create(Department department) {

        if (departmentRepository.existsByName(department.getName())) {
            throw new DepartmentAlreadyExistsException("Department name " + department.getName() + "is already exists");
        }

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartment() {

        return departmentRepository.findAll();
    }

    @Override
    public Department getById(long id) {

        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found"));
    }

    @Override
    public Department update(Department department, long id) {

        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no department with this ID " + id));

        existingDepartment.setName(department.getName());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void delete(long id) {
        departmentRepository.deleteById(id);
    }


}
