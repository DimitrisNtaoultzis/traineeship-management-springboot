package myy803.springboot.traineeship.service;

import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.TraineeshipPosition;


@Service
public interface TraineeshipService {

	void save(TraineeshipPosition position);
	TraineeshipPosition retrievePosition(String username);
	void deletePosition(Integer id);
	void saveLogbook(String logbook);
	TraineeshipPosition retrievePositionById(int id);
}
