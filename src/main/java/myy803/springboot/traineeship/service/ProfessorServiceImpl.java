package myy803.springboot.traineeship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.EvaluationMapper;
import myy803.springboot.traineeship.dao.ProfessorMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.Professor;
import myy803.springboot.traineeship.model.TraineeshipPosition;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	ProfessorMapper professorMapper;
	
	@Autowired
	EvaluationMapper evaluationMapper;
	
	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	@Override
	public Professor retrieveProfile(String username) {
		Optional<Professor> professor = professorMapper.findByUsername(username);
		
		if (professor.isPresent()) {
			return professorMapper.findByUsername(username).get();
		}
		else {
			Professor newProfessor = new Professor();
			newProfessor.setUsername(username);
			
			return newProfessor;
		}
	}

	@Override
	public boolean isProfessorPresent(String username) {
		Optional<Professor> storedProfessor = professorMapper.findByUsername(username);
		return storedProfessor.isPresent();
	}
	
	@Override
	public void saveProfile(Professor professor) {
		professorMapper.save(professor);
	}

	@Override
	public List<TraineeshipPosition> retrieveAssignedPositions() {
		return null;
	}

	@Override
	public void evaluateAssignedPosition(Integer positionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEvaluation(Integer positionId, Evaluation evaluation) {
		Optional<TraineeshipPosition> position = traineeshipMapper.findById(positionId);
		
		evaluationMapper.save(evaluation);
		
		position.get().getEvaluations().add(evaluation);
		
		traineeshipMapper.save(position.get());
		
		
	}

	@Override
	public List<Professor> findAll() {
		// TODO Auto-generated method stub
		return professorMapper.findAll();
	}

}
