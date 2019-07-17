package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.model.CompanyDBRepository;
import com.tw.apistackbase.service.CompanyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Test
    public void should_return_all_company_infomation_when_get_all_company() throws Exception {
        Employee employee = new Employee(11, "we", 12, "male", 400);
        Company conpany = new Company("T1", 12, Arrays.asList(employee));

        when(companyService.fetchAllCompany(null, null)).thenReturn(Arrays.asList(conpany));

        ResultActions result = mvc.perform(get("/companies"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName", is("T1")))
                .andExpect(jsonPath("$[0].employeesNumber", is(12)))
                .andExpect(jsonPath("$[0].employees[0].id", is(11)));
    }

    @Test
    public void should_return_spectify_company_when_get_spectify_company() throws Exception {
        Company conpany = new Company("T1", 12, Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyService.fetchCompanyByCompanyName("T1")).thenReturn(conpany);

        ResultActions result = mvc.perform(get("/companies/{companyName}", conpany.getCompanyName()));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("T1")))
                .andExpect(jsonPath("$.employeesNumber", is(12)))
                .andExpect(jsonPath("$.employees[0].id", is(11)));
    }

    @Test
    public void should_return_spectify_company_when_add_company() throws Exception {
        Company conpany = new Company("T1", 12, Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyService.addCompany(ArgumentMatchers.any())).thenReturn(conpany);

        ResultActions result = mvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(conpany)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("T1")))
                .andExpect(jsonPath("$.employeesNumber", is(12)))
                .andExpect(jsonPath("$.employees[0].id", is(11)));
    }

    @Test
    public void should_return_spectify_company_when_delete_company() throws Exception {
        Company conpany = new Company("T1", 12, Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyService.deleteCompany(ArgumentMatchers.any())).thenReturn(conpany);

        ResultActions result = mvc.perform(delete("/companies/{companyName}", "T1"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("T1")))
                .andExpect(jsonPath("$.employeesNumber", is(12)))
                .andExpect(jsonPath("$.employees[0].id", is(11)));
    }

    @Test
    public void should_return_spectify_company_when_update_company() throws Exception {
        Company conpany = new Company("T1", 12, Arrays.asList(new Employee(11, "we", 12, "male", 400)));

        when(companyService.updateCompany(anyString(), ArgumentMatchers.any())).thenReturn(conpany);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.put("/companies/{companyName}", "T1", conpany).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(conpany)))
                .andExpect(jsonPath("$.companyName", is("T1")))
                .andExpect(jsonPath("$.employeesNumber", is(12)))
                .andExpect(jsonPath("$.employees[0].id", is(11)));
    }
}
