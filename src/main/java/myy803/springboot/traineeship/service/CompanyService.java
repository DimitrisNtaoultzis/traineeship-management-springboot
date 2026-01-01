package myy803.springboot.traineeship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.Company;
import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.TraineeshipPosition;


@Service
public interface CompanyService {
	public Company retrieveProfile(String username);
	public void saveProfile(Company company);
	public boolean isCompanyPresent(String username);
	public List<TraineeshipPosition> retrieveAvailablePositions(String username);
	public void addPosition(String username, TraineeshipPosition position);
	public List<TraineeshipPosition> retrieveAssignedPositions(String username);
	public void evaluateAssignedPosition(Integer positionId);
	public void saveEvaluation(Integer positionId, Evaluation evaluation);
}
