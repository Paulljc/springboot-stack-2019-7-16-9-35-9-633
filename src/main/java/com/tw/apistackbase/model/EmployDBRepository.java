package com.tw.apistackbase.model;

import com.tw.apistackbase.entity.Employee;

import java.util.List;

public interface EmployDBRepository {
    List<Employee> fetchAllEmployees();

    Employee fetchEmployeeById(int id);

    List<Employee> fetchEmployeeByGender(String gender);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    Employee deleteEmployee(int id);

    List<Employee> getCompanyByPagination(Integer page, Integer pageSize);
}
