package myy803.springboot.traineeship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.service.StudentService;
import myy803.springboot.traineeship.service.TraineeshipService;



@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	TraineeshipService traineeshipService;
	
	@RequestMapping("/student/dashboard")
    public String getStudentDashboard(Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
		if (studentService.isStudentPresent(currentUsername)) {
			model.addAttribute("student", studentService.retrieveProfile(currentUsername));
			return "student/dashboard";
		}
		else {
			return "redirect:/student/profile";
		}
    }
	
	@RequestMapping("/student/profile")
    public String retrieveProfile(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
		model.addAttribute("student", studentService.retrieveProfile(currentUsername));
		
		return "student/profile";
    }
	
	@RequestMapping("/student/profile/save")
    public String saveProfile(@ModelAttribute("student") Student student){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    student.setUsername(currentUsername);
		studentService.saveProfile(student);
		
		return "redirect:/student/dashboard";
    }
	
	@RequestMapping("/student/apply")
	public String applyForTraineeship() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
		
	    Student student = studentService.retrieveProfile(currentUsername);
	    student.setLookingForTraineeship(true);
	    studentService.saveProfile(student);
		
		return "redirect:/student/dashboard";
	}
	
	@RequestMapping("/student/fill-logbook")
	public String fillLogbook(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    model.addAttribute("position", traineeshipService.retrievePosition(currentUsername));
	    
		return "student/fill-logbook";
	}
	
	@RequestMapping("/student/savelogbook")
	public String saveLogbook(@RequestParam("logbook") String logbook) {
		traineeshipService.saveLogbook(logbook);
		
		return "redirect:/student/dashboard";
	}
	
	
}

