package myy803.springboot.traineeship;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import myy803.springboot.traineeship.controller.CompanyController;
import myy803.springboot.traineeship.service.CommiteeService;
import myy803.springboot.traineeship.service.CompanyService;
import myy803.springboot.traineeship.service.EvaluationService;
import myy803.springboot.traineeship.service.TraineeshipService;
import myy803.springboot.traineeship.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CompanyController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;
    @MockBean
    private UserService userService;
    @MockBean
    private TraineeshipService traineeshipService;
    @MockBean
    private EvaluationService evaluationService;
    @MockBean
    private CommiteeService commiteeService;
    
    @Test
    @WithMockUser(username = "company", roles = {"COMPANY"})
    void testGetCompanyDashboard() throws Exception {
    	when(companyService.isCompanyPresent("company")).thenReturn(true);
        
    	mockMvc.perform(get("/company/dashboard"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(username = "commitee", roles = {"COMMITEE"})
    void testProfessorNotFound() throws Exception {
       
        mockMvc.perform(get("/commitee/assign-professor/3/interests"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @WithMockUser(username = "company", authorities = {"COMPANY"})
    void testCompanyNotExists() throws Exception {
    	when(companyService.isCompanyPresent("company")).thenReturn(false);
    	mockMvc.perform(get("/company/dashboard"))
                .andExpect(status().is3xxRedirection());
    }
}