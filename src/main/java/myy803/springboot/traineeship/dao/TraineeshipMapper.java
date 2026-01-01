package myy803.springboot.traineeship.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.traineeship.model.Company;
import myy803.springboot.traineeship.model.TraineeshipPosition;


public interface TraineeshipMapper extends JpaRepository<TraineeshipPosition, Integer>{

	List<TraineeshipPosition> findAllByCompany(Optional<Company> optional);
	List<TraineeshipPosition> findAllByIsAssigned(boolean isAssigned);
}
