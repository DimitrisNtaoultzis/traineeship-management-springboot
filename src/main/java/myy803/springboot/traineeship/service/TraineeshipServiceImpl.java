package myy803.springboot.traineeship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Student;
import myy803.springboot.traineeship.model.TraineeshipPosition;

@Service
public class TraineeshipServiceImpl implements TraineeshipService {

	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public void save(TraineeshipPosition position) {
		traineeshipMapper.save(position);
	}

	@Override
	public TraineeshipPosition retrievePosition(String username) {
		Student student = studentMapper.findByUsername(username).get();
		return student.getAssignedPosition();
	}

	@Override
	public void deletePosition(Integer id) {
		traineeshipMapper.deleteById(id);
	}

	@Override
	public void saveLogbook(String logbook) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
		TraineeshipPosition position = studentMapper.findByUsername(username).get().getAssignedPosition();
		position.setStudentLogbook(logbook);
		traineeshipMapper.save(position);
	}

	@SuppressWarnings("deprecation")
	@Override
	public TraineeshipPosition retrievePositionById(int id) {
		return traineeshipMapper.getById(id);
	}

}
