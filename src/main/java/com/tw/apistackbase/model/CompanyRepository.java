package com.tw.apistackbase.model;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CompanyRepository implements CompanyDBRepository {

    private static final Map<String, Company> companies = new HashMap<>();

    static {
        companies.put("T1", createCompany("T1", 2, (List<Employee>) createEmployee(111, "hehe", 11, "male", 4000)));
        companies.put("T2", createCompany("T2", 2, (List<Employee>) createEmployee(114, "heh", 11, "male", 7000)));
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

    private static Company createCompany(String companyName, int employeesNumber, List<Employee> employees) {
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setEmployeesNumber(employeesNumber);
        company.setEmployees(employees);
        return company;
    }

    @Override
    public List<Company> fetchAllCompany(Integer page, Integer pageSize) {
        if (page == null){
            return new ArrayList<>(companies.values());
        }else {
            return companies.values().stream().skip((Math.max(0, page - 1) * pageSize)).limit(pageSize).collect(Collectors.toList());
        }
    }

    @Override
    public Company fetchCompanyByCompanyName(String companyName) {
        return companies.get(companyName);
    }

    @Override
    public List<Employee> fetchEmployeesByCompanyName(String companyName) {
        return companies.get(companyName).getEmployees();
    }

    @Override
    public Company addCompany(Company company) {
        companies.put(company.getCompanyName(), createCompany(company.getCompanyName(), company.getEmployeesNumber(), company.getEmployees()));
        return company;
    }

    @Override
    public Company updateCompany(String companyName, Company company) {
        if (companies.containsKey(companyName)) {
            companies.put(companyName, company);
            return company;
        }
        return null;
    }

    @Override
    public Company deleteCompany(String companyName) {
        return companies.remove(companyName);
    }

}
