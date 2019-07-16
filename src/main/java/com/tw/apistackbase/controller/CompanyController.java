package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping()
    public List<Company> fetchAllCompany(){
        return companyService.fetchAllCompany();
    }

    @GetMapping("/{companyName}")
    public Company fetchCompanyByCompanyName(@PathVariable("companyName")String companyName){
        return companyService.fetchCompanyByCompanyName(companyName);
    }

    @GetMapping("/{companyName}/employees")
    public List<Employee> fetchEmployeesByCompanyName(@PathVariable("companyName")String companyName){
        return companyService.fetchEmployeesByCompanyName(companyName);
    }

    @PostMapping()
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("/{companyName}")
    public Company updateCompany(@PathVariable("companyName") String companyName, @RequestBody Company company){
        return companyService.updateCompany(companyName, company);
    }

    @DeleteMapping("/{companyName}")
    public Company deleteCompany(@PathVariable(value = "companyName") String companyName){
        return companyService.deleteCompany(companyName);
    }

    @GetMapping()
    public List<Company> getCompanyByPagination(Integer page, Integer pageSize) {
        return companyService.getCompanyByPagination(page, pageSize);
    }
}
