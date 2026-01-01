package myy803.springboot.traineeship.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.model.TraineeshipPosition;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	@Override
	public void saveProfile(Student student) {
		studentMapper.save(student);
	}

	@Override
	public Student retrieveProfile(String studentUsername) {
		Optional<Student> student = studentMapper.findByUsername(studentUsername);
		
		if (student.isPresent()) {
			return student.get();
		}
		else {
			Student newStudent = new Student();
			newStudent.setUsername(studentUsername);
			return newStudent;
		}
	}

	@Override
	public void saveLogbook(TraineeshipPosition position) {
		traineeshipMapper.save(position);
	}

	@Override
	public boolean isStudentPresent(String username) {
		Optional<Student> storedStudent = studentMapper.findByUsername(username);
		return storedStudent.isPresent();
	}



}
