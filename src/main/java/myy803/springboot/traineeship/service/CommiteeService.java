package myy803.springboot.traineeship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.model.TraineeshipPosition;



@Service
public interface CommiteeService {
	public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy);
	public List<Student> retrieveTraineeshipApplications();
	public void assignPosition(Integer positionId, String studentUsername);
	public void assignSupervisor(Integer positionId, String strategy);
	public List<TraineeshipPosition> listAssignedTraineeships();
	public void completeAssignedTraineeships(Integer positionId, boolean grade);
}