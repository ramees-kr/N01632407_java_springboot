package com.ems.activity5.controller;

import com.ems.activity5.model.Employee;
import com.ems.activity5.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employeeList", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Employee employee = new Employee(0, "", "", "");
        model.addAttribute("employee", employee);
        return "emp-add-form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "emp-add-form";
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "emp-edit-form";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, @ModelAttribute("employee") @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "emp-edit-form";
        }
        employeeService.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
