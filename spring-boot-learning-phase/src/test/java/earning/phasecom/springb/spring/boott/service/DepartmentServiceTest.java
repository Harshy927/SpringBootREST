package earning.phasecom.springb.spring.boott.service;

import earning.phasecom.springb.spring.boott.entity.Department;
import earning.phasecom.springb.spring.boott.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;


    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CSE").departmentId(3L).departmentCode("CSE-10").departmentAddress("Goa").build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CSE")).thenReturn(department);
    }

    @Test
    @DisplayName("GEt data based on valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound()
    {
        String departmentName = "CSE";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName,found.getDepartmentName());
    }
}