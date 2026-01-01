package myy803.springboot.traineeship.service.strategies;

import java.util.List;

import myy803.springboot.traineeship.model.TraineeshipPosition;



public interface PositionsSearchStrategy {

	List<TraineeshipPosition> search(String applicantUsername);
}
