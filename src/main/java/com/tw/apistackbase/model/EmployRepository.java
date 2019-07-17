package com.tw.apistackbase.model;

import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployRepository implements EmployDBRepository {

    private static final Map<Integer, Employee> employees = new HashMap<>();

    static {
        employees.put(111, createEmployee(111, "hehe", 11, "male", 4000));
        employees.put(112, createEmployee(112, "he", 11, "male", 5000));
        employees.put(113, createEmployee(113, "hhe", 11, "male", 6000));
        employees.put(114, createEmployee(114, "heh", 11, "male", 7000));
    }

    private static Employee createEmployee(int id, String name, int age, String gender, int salary) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);
        employee.setSalary(salary);
        return employee;
    }

    @Override
    public List<Employee> fetchAllEmployees(Integer page, Integer pageSize) {
        if (page == null){
            return new ArrayList<>(employees.values());
        }else {
            return employees.values().stream().skip((Math.max(0, page - 1) * pageSize)).limit(pageSize).collect(Collectors.toList());
        }
    }

    @Override
    public Employee fetchEmployeeById(int id) {
        return employees.get(id);
    }

    @Override
    public List<Employee> fetchEmployeeByGender(String gender) {
        return employees.values().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employees.put(employee.getId(), createEmployee(employee.getId(), employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary()));
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        if (employees.containsKey(id)) {
            employees.put(id, employee);
            return employee;
        }
        return null;
    }

    @Override
    public Employee deleteEmployee(int id) {
        return employees.remove(id);
    }

}
