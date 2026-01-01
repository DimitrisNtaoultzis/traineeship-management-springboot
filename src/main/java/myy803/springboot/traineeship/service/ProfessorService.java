package myy803.springboot.traineeship.service;

import java.util.List;

import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.Professor;
import myy803.springboot.traineeship.model.TraineeshipPosition;


public interface ProfessorService {
	Professor retrieveProfile(String username);
	boolean isProfessorPresent(String username);
	void saveProfile(Professor professor);
	List<TraineeshipPosition> retrieveAssignedPositions();
	void evaluateAssignedPosition(Integer positionId);
	void saveEvaluation(Integer positionId, Evaluation evaluation);
	List<Professor> findAll();
}
