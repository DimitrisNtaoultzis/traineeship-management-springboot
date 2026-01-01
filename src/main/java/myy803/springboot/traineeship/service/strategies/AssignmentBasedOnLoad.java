package myy803.springboot.traineeship.service.strategies;

import org.springframework.stereotype.Component;

import myy803.springboot.traineeship.dao.ProfessorMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Professor;
import myy803.springboot.traineeship.model.TraineeshipPosition;


@Component
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

	ProfessorMapper professorMapper; 
	TraineeshipMapper traineeshipMapper;
	
	public AssignmentBasedOnLoad(ProfessorMapper professorMapper, TraineeshipMapper traineeshipMapper) {
		this.professorMapper = professorMapper;
		this.traineeshipMapper = traineeshipMapper;
	}
	
	@Override
	public void assign(Integer positionId) {
		TraineeshipPosition position = traineeshipMapper.findById(positionId).get();
				
        
        if (professorMapper.findAll().size() > 0 ) {
	        Professor bestProfessor = professorMapper.findAll().get(0);
	        double numberOfSupervised = bestProfessor.getSupervisedPositions().size();
	        	        
			for (Professor p: professorMapper.findAll()) {
				
		        if (p.getSupervisedPositions().size() < numberOfSupervised) {
		        	bestProfessor = p;
		        	numberOfSupervised = p.getSupervisedPositions().size();
		        }
			}
			
			// assign best professor result
			position.setSupervisor(bestProfessor);
			traineeshipMapper.save(position);
				
			bestProfessor.getSupervisedPositions().add(position);
			professorMapper.save(bestProfessor);
			

        }

	}

}
