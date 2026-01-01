package myy803.springboot.traineeship.service.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.TraineeshipPosition;


@Component
public class SearchBasedOnLocation implements PositionsSearchStrategy {

	TraineeshipMapper traineeshipMapper;
	StudentMapper studentMapper;
	
	public SearchBasedOnLocation(TraineeshipMapper traineeshipMapper, StudentMapper studentMapper) {
		this.traineeshipMapper = traineeshipMapper;
		this.studentMapper = studentMapper;
	}
	
	@Override
	public List<TraineeshipPosition> search(String applicantUsername) {
		List<TraineeshipPosition> results = new ArrayList<TraineeshipPosition>();
		String skillsString = studentMapper.findByUsername(applicantUsername).get().getSkills();
		Set<String> skills = new HashSet<>();
	    for (String s : skillsString.split(",")) {
	    	skills.add(s.trim());
	    }
		for (TraineeshipPosition p: traineeshipMapper.findAll()) {
			String pSkillsString = p.getSkills();
	        Set<String> pSkills = new HashSet<>();
	        for (String t : pSkillsString.split(",")) {
	        	pSkills.add(t.trim());
	        }
	        Set<String> pIntersection = new HashSet<>(skills);
	        pIntersection.retainAll(pSkills);

	        Set<String> pUnion = new HashSet<>(skills);
	        pUnion.addAll(pSkills);
	        
	        double pJaccard = 0; 
	        if (!pUnion.isEmpty()) {
	            pJaccard = (double) pIntersection.size() / pUnion.size();
	        }
	        
			if (!p.isAssigned() && pJaccard >= 0.5 && p.getCompany().getCompanyLocation().equals(studentMapper.findByUsername(applicantUsername).get().getPreferredLocation())) {
				results.add(p);
			}
		}
		return results;
	}

}
