package myy803.springboot.traineeship.service.strategies;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import myy803.springboot.traineeship.dao.ProfessorMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Professor;
import myy803.springboot.traineeship.model.TraineeshipPosition;


@Component
public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {


	TraineeshipMapper traineeshipMapper;
	
	ProfessorMapper professorMapper; 
	
	public AssignmentBasedOnInterests(ProfessorMapper professorMapper, TraineeshipMapper traineeshipMapper) {
		this.professorMapper = professorMapper;
		this.traineeshipMapper = traineeshipMapper;
	}

	@Override
	public void assign(Integer positionId) {
		TraineeshipPosition position = traineeshipMapper.findById(positionId).get();
		String topicsString = position.getTopics();
        Set<String> topics = new HashSet<>();
        for (String t : topicsString.split(",")) {
            topics.add(t.trim());
        }
		
        
        if (professorMapper.findAll().size() > 0 ) {
	        Professor bestProfessor = professorMapper.findAll().get(0);
	        double bestSimilarity = 0;
	        
			for (Professor p: professorMapper.findAll()) {
				String interestsString = p.getInterests();
		        Set<String> interests = new HashSet<>();
		        for (String t : interestsString.split(",")) {
		            interests.add(t.trim());
		        }
		        
		        Set<String> intersection = new HashSet<>(topics);
		        intersection.retainAll(interests);
		        Set<String> union = new HashSet<>(topics);
		        union.addAll(interests);
		        
		        
		        double jaccard = 0; 
		        if (!union.isEmpty()) {
		            jaccard = (double) intersection.size() / union.size();
		        }
	
		        if (jaccard >= 0.5 && jaccard >= bestSimilarity) {
		        	bestProfessor = p;
		        	bestSimilarity = jaccard;
		        }
			}
			
			// assign best professor result
			if (bestSimilarity > 0) {
				
				position.setSupervisor(bestProfessor);
				traineeshipMapper.save(position);
				
				bestProfessor.getSupervisedPositions().add(position);
				professorMapper.save(bestProfessor);
			}

        }
		
	}

}
