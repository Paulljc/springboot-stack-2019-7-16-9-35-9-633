package com.tw.apistackbase;


import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.model.EmployDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployDBRepository employDBRepository;

    @Test
    public void should_return_all_employee_when_fetch_all_employee() throws Exception {
        Employee employee = new Employee(1111, "we", 18, "male", 2000);
        List<Employee> employees = Arrays.asList(employee);

        when(employDBRepository.fetchAllEmployees()).thenReturn(employees);

        ResultActions result = mockMvc.perform(get("/employees"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("we")))
                .andExpect(jsonPath("$[0].age", is(18)))
                .andExpect(jsonPath("$[0].id", is(1111)))
                .andExpect(jsonPath("$[0].salary", is(2000)))
                .andExpect(jsonPath("$[0].gender", is("male")));
    }

    @Test
    public void should_return_spectify_employee_when_find_employee_by_id() throws Exception {
        Employee employee = new Employee(1111, "we", 18, "male", 2000);

        when(employDBRepository.fetchEmployeeById(anyInt())).thenReturn(employee);

        ResultActions result = mockMvc.perform(get("/employees/{employeeId}", employee.getId()));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("we")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.id", is(1111)))
                .andExpect(jsonPath("$.salary", is(2000)))
                .andExpect(jsonPath("$.gender", is("male")));

    }

    @Test
    public void should_return_correspond_employees_when_employeeService_find_employee_by_gender() throws Exception {
        Employee employee = new Employee(1111, "we", 18, "male", 2000);

        when(employDBRepository.fetchEmployeeByGender(anyString())).thenReturn(Arrays.asList(employee));

        ResultActions result = mockMvc.perform(get("/employees/").param("gender", "male"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("we")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.id", is(1111)))
                .andExpect(jsonPath("$.salary", is(2000)))
                .andExpect(jsonPath("$.gender", is("male")));
    }

    @Test
    public void should_return_employee_when_create_a_new_employee() throws Exception {
        Employee employee = new Employee(1111, "we", 18, "male", 2000);

        String requestBody = "{\n" +
                "        \"id\": 1111,\n" +
                "        \"name\": \"we\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 2000\n" +
                "    }";

        when(employDBRepository.addEmployee(any())).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("we")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.id", is(1111)))
                .andExpect(jsonPath("$.salary", is(2000)))
                .andExpect(jsonPath("$.gender", is("male")));
    }

    @Test
    public void should_return_employee_when_update_an_employee_infomation() throws Exception{
        Employee employee = new Employee(1111, "we", 18, "male", 2000);

        String requestBody = "{\n" +
                "        \"id\": 1112,\n" +
                "        \"name\": \"we\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 2000\n" +
                "    }";

        when(employDBRepository.updateEmployee(anyInt(),any())).thenReturn(employee);

            mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", 1111).contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is("we")))
                    .andExpect(jsonPath("$.age", is(18)))
                    .andExpect(jsonPath("$.id", is(1111)))
                    .andExpect(jsonPath("$.salary", is(2000)))
                    .andExpect(jsonPath("$.gender", is("male")));
    }

    @Test
    public void should_return_correspond_employee_when_employeeService_delete_employee() throws Exception{
        Employee employee = new Employee(12, "we", 18, "male", 2000);

            when(employDBRepository.deleteEmployee(anyInt())).thenReturn(employee);

            mockMvc.perform(delete("/employees/{employeeId}", 12))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is("we")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.id", is(12)))
                .andExpect(jsonPath("$.salary", is(2000)))
                .andExpect(jsonPath("$.gender", is("male")));
    }
}
