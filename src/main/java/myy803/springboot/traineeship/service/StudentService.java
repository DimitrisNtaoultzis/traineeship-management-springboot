package myy803.springboot.traineeship.service;

import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.model.TraineeshipPosition;


@Service
public interface StudentService {

	void saveProfile(Student student);
	Student retrieveProfile(String studentUsername);
	void saveLogbook(TraineeshipPosition position);
	public boolean isStudentPresent(String username);
}
