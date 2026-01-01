package myy803.springboot.traineeship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.springboot.traineeship.model.Company;
import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.EvaluationType;
import myy803.springboot.traineeship.model.TraineeshipPosition;
import myy803.springboot.traineeship.service.CompanyService;
import myy803.springboot.traineeship.service.EvaluationService;
import myy803.springboot.traineeship.service.TraineeshipService;
import myy803.springboot.traineeship.service.UserService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService; 
	
	@Autowired
	UserService userService;
	
	@Autowired
	TraineeshipService traineeshipService;
	
	@Autowired
	EvaluationService evaluationService;
	
	@RequestMapping("/company/dashboard")
	public String getCompanyDashboard() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
		if (companyService.isCompanyPresent(currentUsername)) {
			return "company/dashboard";
		}
		else {
			return "redirect:/company/create";
		}
	}
	
	@RequestMapping("/company/create")
	public String getCreateForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
		Company company = new Company();
		company.setUsername(currentUsername);
		
		model.addAttribute("company", company);
		return "company/create";
	}
	
	@PostMapping("/company/save")
	public String createCompany(@ModelAttribute("company") Company company) {
		companyService.saveProfile(company);
		
		return "redirect:/company/dashboard";
	}
	
	@RequestMapping("/company/free-positions")
	public String getFreePositionsList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    model.addAttribute("positions", companyService.retrieveAvailablePositions(currentUsername));
		
		return "company/free-positions";
	}
	
	@RequestMapping("/company/assigned-positions")
	public String getAssignedPositionsList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    model.addAttribute("positions", companyService.retrieveAssignedPositions(currentUsername));
		
		return "company/assigned-positions";
	}
	
	@RequestMapping("/company/new-position")
	public String getNewPositionForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    TraineeshipPosition position = new TraineeshipPosition();
		Company company = companyService.retrieveProfile(currentUsername);
	    position.setCompany(company);
	    company.getPositions().add(position);
		
		model.addAttribute("position", position);
		
		return "company/new-position";
	}
	
	@RequestMapping("/company/create-position")
	public String createNewPosition(@ModelAttribute("position") TraineeshipPosition position) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
	    Company company = companyService.retrieveProfile(currentUsername);
	    position.setStudent(null);
	    position.setCompany(company);
		
		traineeshipService.save(position);
		
		return "redirect:/company/dashboard";
	}
	
	@PostMapping("/company/delete-position/{id}")
	public String deletePosition(@PathVariable Integer id) {
		
		traineeshipService.deletePosition(id);
		
		return "redirect:/company/dashboard";
	}
	
	@RequestMapping("/company/assigned-positions/{id}/evaluation")
	public String getEvaluationForm(Model model, @PathVariable int id) {
		model.addAttribute("posId", id);
		model.addAttribute("evaluation", new Evaluation());
		return "company/evaluation";
	}
	
	@RequestMapping("/company/assigned-positions/{id}/evaluation/save")
	public String saveEvaluation(@ModelAttribute Evaluation evaluation, @PathVariable int id) {
		evaluation.setEvaluationType(EvaluationType.COMPANY);
		companyService.saveEvaluation(id, evaluation);
		return "redirect:/company/dashboard";
	}
	
}
