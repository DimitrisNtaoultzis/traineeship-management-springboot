package myy803.springboot.traineeship.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.ProfessorMapper;
import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.model.TraineeshipPosition;
import myy803.springboot.traineeship.service.strategies.PositionsSearchFactory;
import myy803.springboot.traineeship.service.strategies.PositionsSearchStrategy;
import myy803.springboot.traineeship.service.strategies.SupervisorAssignmentFactory;
import myy803.springboot.traineeship.service.strategies.SupervisorAssignmentStrategy;


@Service
public class CommiteeServiceImpl implements CommiteeService {

	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	@Autowired
	ProfessorMapper professorMapper;
	
	@Autowired
	PositionsSearchFactory searchFactory;
	
	@Autowired
	SupervisorAssignmentFactory assignmentFactory;
	
	@Override
	public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy) {
		
		PositionsSearchStrategy searchStrategy = searchFactory.create(strategy);
		
		return searchStrategy.search(applicantUsername);
	}

	@Override
	public List<Student> retrieveTraineeshipApplications() {
		List<Student> students = new ArrayList<Student>();
		
		for (Student student: studentMapper.findAll()) {
			if (student.isLookingForTraineeship() && student.getAssignedPosition() == null) {
				students.add(student);
			}
		}
		
		return students;
	}

	@Override
	public void assignPosition(Integer positionId, String studentUsername) {
		Student student = studentMapper.findByUsername(studentUsername).get();
		TraineeshipPosition position = traineeshipMapper.findById(positionId).get();
		
		student.setAssignedPosition(position);
		studentMapper.save(student);
		
		position.setAssigned(true);
		position.setStudent(student);
		traineeshipMapper.save(position);
	}

	@Override
	public void assignSupervisor(Integer positionId, String strategy) {
		
		SupervisorAssignmentStrategy assignmentStrategy = assignmentFactory.create(strategy);
		
		assignmentStrategy.assign(positionId);

	}

	@Override
	public List<TraineeshipPosition> listAssignedTraineeships() {
		return traineeshipMapper.findAllByIsAssigned(true);
	}

	@Override
	public void completeAssignedTraineeships(Integer positionId, boolean grade) {
		TraineeshipPosition position = traineeshipMapper.findById(positionId).get();
		position.setPassFailGrade(grade);
		traineeshipMapper.save(position);
	}


}
