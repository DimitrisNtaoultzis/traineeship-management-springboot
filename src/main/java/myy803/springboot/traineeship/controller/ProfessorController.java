package myy803.springboot.traineeship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.EvaluationType;
import myy803.springboot.traineeship.model.Professor;
import myy803.springboot.traineeship.service.ProfessorService;


@Controller
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@RequestMapping("/professor/dashboard")
    public String getStudentDashboard(Model model){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
		if (professorService.isProfessorPresent(currentUsername)) {
			model.addAttribute("student", professorService.retrieveProfile(currentUsername));
			return "professor/dashboard";
		}
		else {
			return "redirect:/professor/profile";
		}
    }
	
	@RequestMapping("/professor/profile")
	public String getProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    model.addAttribute("professor", professorService.retrieveProfile(currentUsername));
		
		return "/professor/profile";
	}
	
	@RequestMapping("/professor/profile/save")
	public String saveeProfile(@ModelAttribute("professor") Professor professor) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    professor.setUsername(currentUsername);
		professorService.saveProfile(professor);
		
		return "redirect:/professor/dashboard";
	}
	
	@RequestMapping("/professor/positions")
	public String getPositions(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    model.addAttribute("positions", professorService.retrieveProfile(currentUsername).getSupervisedPositions());
		
		return "/professor/positions";
	}
	
	@RequestMapping("/professor/positions/{id}/evaluation")
	public String getEvaluation(Model model, @PathVariable int id) {
		model.addAttribute("posId", id);
		model.addAttribute("evaluation", new Evaluation());
		return "/professor/evaluation";
	}
	
	@RequestMapping("/professor/positions/{id}/evaluation/save")
	public String saveEvaluation(@ModelAttribute Evaluation evaluation, @PathVariable int id) {
		evaluation.setEvaluationType(EvaluationType.PROFESSOR);
		professorService.saveEvaluation(id, evaluation);
		return "redirect:/professor/dashboard";
	}

}
