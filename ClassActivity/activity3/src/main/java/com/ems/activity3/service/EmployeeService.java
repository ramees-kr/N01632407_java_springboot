package com.ems.activity3.service;
import java.util.ArrayList;
import java.util.List;
import com.ems.activity3.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeService() {
        employeeList.add(new Employee(1, "Ramees", "KR", "ramees.kr@admin.com"));
        employeeList.add(new Employee(2, "Reshma", "Roohi", "reshma.roohi@admin.com"));
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Employee getEmployeeById(int id) {
        return employeeList.stream()
                .filter(employee -> employee.getEmp_id() == id)
                .findFirst()
                .orElse(null);
    }

    public void addEmployee(Employee employee) {
        employee.setEmp_id(employeeList.size() + 1);
        employeeList.add(employee);
    }

    public void updateEmployee(int id, Employee updatedEmployee) {
        employeeList.removeIf(employee -> employee.getEmp_id() == id);
        updatedEmployee.setEmp_id(id);
        employeeList.add(updatedEmployee);
    }

    public void deleteEmployee(int id) {
        employeeList.removeIf(employee -> employee.getEmp_id() == id);
    }
}
