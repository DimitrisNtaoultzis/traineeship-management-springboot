package myy803.springboot.traineeship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.CompanyMapper;
import myy803.springboot.traineeship.dao.EvaluationMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Company;
import myy803.springboot.traineeship.model.Evaluation;
import myy803.springboot.traineeship.model.TraineeshipPosition;



@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private TraineeshipMapper traineeshipMapper;
	@Autowired
	EvaluationMapper evaluationMapper;
	
	@Override
	public boolean isCompanyPresent(String username) {
		Optional<Company> storedCompany = companyMapper.findByUsername(username);
		return storedCompany.isPresent();
	}

	@Override
	public void saveProfile(Company company) {
		companyMapper.save(company);
	}

	@Override
	public Company retrieveProfile(String username) {
		return companyMapper.findByUsername(username).get();
	}

	@Override
	public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
		List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();
		Optional<Company> company = companyMapper.findByUsername(username);
		
		for (TraineeshipPosition position: traineeshipMapper.findAllByCompany(company)) {
			if (!position.isAssigned()) {
				positions.add(position);
			}
		}
		
		return positions;
	}

	@Override
	public void addPosition(String username, TraineeshipPosition position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
		List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();
		Optional<Company> company = companyMapper.findByUsername(username);
		
		for (TraineeshipPosition position: traineeshipMapper.findAllByCompany(company)) {
			if (position.isAssigned()) {
				positions.add(position);
			}
		}
		
		return positions;
	}

	@Override
	public void evaluateAssignedPosition(Integer positionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEvaluation(Integer positionId, Evaluation evaluation) {
		Optional<TraineeshipPosition> position = traineeshipMapper.findById(positionId);
		
		evaluationMapper.save(evaluation);
		
		position.get().getEvaluations().add(evaluation);
		
		traineeshipMapper.save(position.get());
		
		
	}

}
