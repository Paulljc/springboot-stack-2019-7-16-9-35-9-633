package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.model.CompanyDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyDBRepository companyDBRepository;

    @Test
    public void should_return_all_company_infomation_when_get_all_company() throws Exception {
        Company conpany = new Company();
        conpany.setCompanyName("T1");
        conpany.setEmployeesNumber(12);
        conpany.setEmployees(Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyDBRepository.fetchAllCompany()).thenReturn(Arrays.asList(conpany));

        ResultActions result = mvc.perform(get("/companies"));
        ;
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName", is("we")))
                .andExpect(jsonPath("$[0].employeesNumber", is(12)))
                .andExpect(jsonPath("$[0].employees", is("male")));
    }

    @Test
    public void should_return_spectify_company_infomation_when_get_spectify_company() throws Exception {
        Company conpany = new Company();
        conpany.setCompanyName("T1");
        conpany.setEmployeesNumber(12);
        conpany.setEmployees(Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyDBRepository.fetchCompanyByCompanyName("T1")).thenReturn(conpany);

        ResultActions result = mvc.perform(get("/companies/{companyName}"));
        ;
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName", is("we")))
                .andExpect(jsonPath("$[0].employeesNumber", is(12)))
                .andExpect(jsonPath("$[0].employees", is("male")));
    }
    
}
