package com.tw.apistackbase.model;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;

import java.util.List;

public interface CompanyDBRepository {
    List<Company> fetchAllCompany(Integer page, Integer pageSize);

    Company fetchCompanyByCompanyName(String companyName);

    List<Employee> fetchEmployeesByCompanyName(String companyName);

    Company addCompany(Company company);

    Company updateCompany(String companyName, Company company);

    Company deleteCompany(String companyName);

}
