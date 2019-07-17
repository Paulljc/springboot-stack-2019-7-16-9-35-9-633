package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.model.EmployDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployDBRepository employDBRepository;

    public List<Employee> fetchAllEmployees(Integer page, Integer pageSize) {
        return employDBRepository.fetchAllEmployees(page, pageSize);
    }

    public Employee fetchEmployeeById(int id){
        return employDBRepository.fetchEmployeeById(id);
    }

    public List<Employee> fetchEmployeeByGender(String gender){
        return employDBRepository.fetchEmployeeByGender(gender);
    }

    public Employee addEmployee(Employee employee){
        return employDBRepository.addEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee employee){
        return employDBRepository.updateEmployee(id, employee);
    }

    public Employee deleteEmployee(int id){
        return employDBRepository.deleteEmployee(id);
    }

}
