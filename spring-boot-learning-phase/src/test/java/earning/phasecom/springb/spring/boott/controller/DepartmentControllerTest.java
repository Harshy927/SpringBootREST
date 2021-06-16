package earning.phasecom.springb.spring.boott.controller;

import earning.phasecom.springb.spring.boott.entity.Department;
import earning.phasecom.springb.spring.boott.error.DepartmentNotFoundException;
import earning.phasecom.springb.spring.boott.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {


    private Department department;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @BeforeEach
    void setUp(){
         department = Department.builder().
                departmentCode("CSE-101").departmentAddress("PONDA").departmentName("Computer").
                departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder().
                departmentCode("CSE-101").departmentAddress("PONDA").departmentName("Computer").
                departmentId(1L)
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "\t\"departmentName\":\"Computer\",\n" +
                        "\t\"departmentAddress\":\"PONDA\",\n" +
                        "\t\"departmentCode\":\"CSE-101\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/departments/1").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());


    }
}