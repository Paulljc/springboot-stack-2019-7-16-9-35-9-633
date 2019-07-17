package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public List<Employee> fetchAllEmployees(Integer page, Integer pageSize){
        return employeeService.fetchAllEmployees(page, pageSize);
    }

    @GetMapping("/{id}")
    public Employee fetchEmployeeById(@PathVariable("id")int id){
        return employeeService.fetchEmployeeById(id);
    }

    @GetMapping(params = "gender")
    public List<Employee> fetchEmployeeByGender(@RequestParam String gender){
        return employeeService.fetchEmployeeByGender(gender);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable(value = "id") int id){
        return employeeService.deleteEmployee(id);
    }
}
