package myy803.springboot.traineeship;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

	@Autowired
    private MockMvc mockMvc;

	@Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("auth/signin")); 
    }
		
	@Test
	@WithMockUser(username = "company", roles = {"COMPANY"})
	void testLogout() throws Exception {
	    mockMvc.perform(get("/logout"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/")); 
	}
	
	@Test
	void testAccessToDashboardWithoutAuth() throws Exception {
	    mockMvc.perform(get("/company/dashboard"))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("http://localhost/login"));
	}
}
