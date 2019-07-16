package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.model.CompanyDBRepository;
import com.tw.apistackbase.model.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyDBRepository companyDBRepository;

    public List<Company> fetchAllCompany() {
        return companyDBRepository.fetchAllCompany();
    }

    public Company fetchCompanyByCompanyName(String companyName){
        return companyDBRepository.fetchCompanyByCompanyName(companyName);
    }

    public List<Employee> fetchEmployeesByCompanyName(String companyName){
        return companyDBRepository.fetchEmployeesByCompanyName(companyName);
    }

    public Company addCompany(Company company){
        return companyDBRepository.addCompany(company);
    }

    public Company updateCompany(String companyName, Company company){
        return companyDBRepository.updateCompany(companyName, company);
    }

    public Company deleteCompany(String companyName){
        return companyDBRepository.deleteCompany(companyName);
    }

    public List<Company> getCompanyByPagination(Integer page, Integer pageSize) {
        return companyDBRepository.getCompanyByPagination(page, pageSize);
    }
}
