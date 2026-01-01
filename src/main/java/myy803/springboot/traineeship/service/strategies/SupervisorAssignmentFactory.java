package myy803.springboot.traineeship.service.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.traineeship.dao.ProfessorMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;



@Component
public class SupervisorAssignmentFactory {

	@Autowired
	ProfessorMapper professorMapper; 
	
	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	public SupervisorAssignmentStrategy create(String strategy) {
		
		if (strategy.equals("interests")) {
			return new AssignmentBasedOnInterests(professorMapper, traineeshipMapper);
		}
		else {
			return new AssignmentBasedOnLoad(professorMapper, traineeshipMapper);
		}
	}
}
