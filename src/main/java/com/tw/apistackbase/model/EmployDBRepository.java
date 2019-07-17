package com.tw.apistackbase.model;

import com.tw.apistackbase.entity.Employee;

import java.util.List;

public interface EmployDBRepository {
    List<Employee> fetchAllEmployees(Integer page, Integer pageSize);

    Employee fetchEmployeeById(int id);

    List<Employee> fetchEmployeeByGender(String gender);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    Employee deleteEmployee(int id);
}
