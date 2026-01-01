package myy803.springboot.traineeship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.springboot.traineeship.model.EvaluationType;
import myy803.springboot.traineeship.model.TraineeshipPosition;
import myy803.springboot.traineeship.service.CommiteeService;
import myy803.springboot.traineeship.service.ProfessorService;
import myy803.springboot.traineeship.service.TraineeshipService;


@Controller
public class CommiteeController {

	@Autowired
	CommiteeService commiteeService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	TraineeshipService traineeshipService;
	
	@RequestMapping("/commitee/dashboard")
	public String getDashboard() {
		
		return "commitee/dashboard";
	}
	
	@RequestMapping("/commitee/students")
	public String getAvailableStudents(Model model) {
		
		model.addAttribute("students", commiteeService.retrieveTraineeshipApplications());
		
		return "commitee/students";
	}
	
	@RequestMapping("/commitee/in-progress-traineeships")
	public String getInProgressTraineeships(Model model) {
		
		model.addAttribute("positions", commiteeService.listAssignedTraineeships());
		
		return "commitee/assigned-positions";
	}
		
	@RequestMapping("/commitee/assign-professor/{id}/interests")
	public String assignProfessorBasedOnInterests(@PathVariable int id) {
		commiteeService.assignSupervisor(id, "interests");
		
		return "redirect:/commitee/dashboard";
	}
	
	@RequestMapping("/commitee/assign-professor/{id}/workload")
	public String assignProfessorBasedOnWorkload(@PathVariable int id) {
		commiteeService.assignSupervisor(id, "workload");
				
		
		return "redirect:/commitee/dashboard";
	}
	
	@RequestMapping("/commitee/students/{username}/interests")
	public String getTraineeshipsByInterests(@PathVariable String username, Model model) {
		
		model.addAttribute("positions", commiteeService.retrievePositionsForApplicant(username, "interests"));
		
		return "commitee/results"; 
	}
	
	@RequestMapping("/commitee/students/{username}/location")
	public String getTraineeshipsByLocation(@PathVariable String username, Model model) {
		
		model.addAttribute("positions", commiteeService.retrievePositionsForApplicant(username, "location"));
		
		return "commitee/results"; 
	}
	
	@RequestMapping("/commitee/students/{username}/both")
	public String getTraineeshipsByInterestsAndLocation(@PathVariable String username, Model model) {
		
		model.addAttribute("positions", commiteeService.retrievePositionsForApplicant(username, "both"));
		
		return "commitee/results"; 
	}
	
	@RequestMapping("/commitee/students/{username}/assign/{id}")
	public String assignStudent(@PathVariable String username, @PathVariable int id) {
		
		commiteeService.assignPosition(id, username);
		
		return "redirect:/commitee/dashboard";
	}
	

	@RequestMapping("/commitee/positions/{id}/final")
	public String getFinalEvaluation(@PathVariable int id, Model model) {
		TraineeshipPosition position = traineeshipService.retrievePositionById(id);
		
		EvaluationType type1 = position.getEvaluations().get(0).getEvaluationType();
		if (type1.equals(EvaluationType.COMPANY)) {
			model.addAttribute("compEvaluation", position.getEvaluations().get(0));
			model.addAttribute("profEvaluation", position.getEvaluations().get(1));
		}
		else {
			model.addAttribute("compEvaluation", position.getEvaluations().get(1));
			model.addAttribute("profEvaluation", position.getEvaluations().get(0));
		}
		
		model.addAttribute("id", id);
				
		return "commitee/final";
	}
	
	@RequestMapping("/commitee/positions/{id}/final/{grade}")
	public String saveFinalGrade(@PathVariable int id, @PathVariable boolean grade) {
		commiteeService.completeAssignedTraineeships(id, grade);
		
		return "redirect:/commitee/dashboard";
	}
}
