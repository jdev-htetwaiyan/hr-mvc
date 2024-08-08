package com.jdev.hr_mvc.controllers;

import com.jdev.hr_mvc.models.Employee;
import com.jdev.hr_mvc.services.DepartmentService;
import com.jdev.hr_mvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        return employeeService.getById(id)
                              .map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable long id) {
        return ResponseEntity.ok(employeeService.update(employee, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        employeeService.delete(id);

        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/assign-department")
    public ResponseEntity<String> assignEmployeeToDepartment(@RequestBody Map<String, Object> requestBody) {
        long departmentId = ((Number) requestBody.get("departmentId")).longValue();

        List<?> employeeIdsRaw = (List<?>) requestBody.get("employeeIds");
        List<Long> employeeIds = new ArrayList<>();
        for (Object id : employeeIdsRaw) {
            employeeIds.add(((Number) id).longValue());
        }

        employeeService.assignEmployeeToDepartment(departmentId, employeeIds);

        return ResponseEntity.ok("Employees have been assigned");
    }

    @PostMapping("/assign-position")
    public ResponseEntity<String> assignPositionToEmployee(@RequestBody Map<String, Object> requestBody) {
        long positionId = ((Number) requestBody.get("positionId")).longValue();

        List<?> employeeIdsRaw = (List<?>) requestBody.get("employeeIds");
        List<Long> employeeIds = new ArrayList<>();
        for (Object id : employeeIdsRaw) {
            employeeIds.add(((Number) id).longValue());
        }

        employeeService.assignPositionToEmployee(positionId, employeeIds);

        return ResponseEntity.ok("Employees have been assigned");
    }


}
