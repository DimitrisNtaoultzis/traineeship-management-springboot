package myy803.springboot.traineeship.service;

import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.Evaluation;

@Service
public interface EvaluationService {
	void saveEvaluation(Evaluation evaluation);
}
