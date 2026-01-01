package myy803.springboot.traineeship.service.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;



@Component
public class PositionsSearchFactory {

	@Autowired
	TraineeshipMapper traineeshipMapper;
	
	@Autowired
    StudentMapper studentMapper;
	
	
	public PositionsSearchStrategy create(String strategy) {
		
		if (strategy.equals("interests")) {
			return new SearchBasedOnInterests(traineeshipMapper, studentMapper);
		}
		else if (strategy.equals("location")) {
			return new SearchBasedOnLocation(traineeshipMapper, studentMapper);
		}
		else {
			return new SearchBasedOnInterestsAndLocation(traineeshipMapper, studentMapper);
		}

	}
}
