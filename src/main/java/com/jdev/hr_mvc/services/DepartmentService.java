package com.jdev.hr_mvc.services;

import com.jdev.hr_mvc.models.Department;
import com.jdev.hr_mvc.models.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department create(Department department);

    List<Department> getAllDepartment();

    Department getById(long id);

    Department update(Department department, long id);

    void delete(long id);
}
