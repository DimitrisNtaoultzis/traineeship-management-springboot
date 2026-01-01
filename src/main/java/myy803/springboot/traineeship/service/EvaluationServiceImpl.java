package myy803.springboot.traineeship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.dao.EvaluationMapper;
import myy803.springboot.traineeship.model.Evaluation;



@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Autowired
	EvaluationMapper evaluationMapper;
	
	@Override
	public void saveEvaluation(Evaluation evaluation) {
		evaluationMapper.save(evaluation);
	}

}
