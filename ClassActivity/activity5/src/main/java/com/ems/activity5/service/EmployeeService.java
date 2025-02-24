package com.ems.activity5.service;

import com.ems.activity5.model.Employee;
import com.ems.activity5.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor injection for EmployeeRepository
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by id
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Add new employee
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Update employee details
    public void updateEmployee(int id, Employee updatedEmployee) {
        // Retrieve the existing employee from the database
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setEmp_firstname(updatedEmployee.getEmp_firstname());
            existingEmployee.setEmp_lastname(updatedEmployee.getEmp_lastname());
            existingEmployee.setEmp_email(updatedEmployee.getEmp_email());
            employeeRepository.save(existingEmployee);
        }
    }

    // Delete employee by id
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
