package myy803.springboot.traineeship.service.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import myy803.springboot.traineeship.dao.StudentMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.TraineeshipPosition;



public class SearchBasedOnInterestsAndLocation implements PositionsSearchStrategy {

	TraineeshipMapper traineeshipMapper;
	StudentMapper studentMapper;
	
	public SearchBasedOnInterestsAndLocation(TraineeshipMapper traineeshipMapper, StudentMapper studentMapper) {
		this.traineeshipMapper = traineeshipMapper;
		this.studentMapper = studentMapper;
	}
	
	@Override
	public List<TraineeshipPosition> search(String applicantUsername) {
		String interestsString = studentMapper.findByUsername(applicantUsername).get().getInterests();
		Set<String> interests = new HashSet<>();
	    for (String s : interestsString.split(",")) {
	        interests.add(s.trim());
	    }
	    String skillsString = studentMapper.findByUsername(applicantUsername).get().getSkills();
	    Set<String> skills = new HashSet<>();
	    for (String s : skillsString.split(",")) {
	    	skills.add(s.trim());
	    }
	    
	    List<TraineeshipPosition> results = new ArrayList<>();		
	    
	    for (TraineeshipPosition p : traineeshipMapper.findAll()) {
	        String topicsString = p.getTopics();
	        Set<String> topics = new HashSet<>();
	        for (String t : topicsString.split(",")) {
	            topics.add(t.trim());
	        }
	        String pSkillsString = p.getSkills();
	        Set<String> pSkills = new HashSet<>();
	        for (String t : pSkillsString.split(",")) {
	        	pSkills.add(t.trim());
	        }
	        
	        Set<String> intersection = new HashSet<>(interests);
	        intersection.retainAll(topics);

	        Set<String> union = new HashSet<>(interests);
	        union.addAll(topics);
	        
	        double jaccard = 0; 
	        if (!union.isEmpty()) {
	            jaccard = (double) intersection.size() / union.size();
	        }

	        Set<String> pIntersection = new HashSet<>(skills);
	        pIntersection.retainAll(pSkills);

	        Set<String> pUnion = new HashSet<>(skills);
	        pUnion.addAll(pSkills);
	        
	        double pJaccard = 0; 
	        if (!pUnion.isEmpty()) {
	            pJaccard = (double) pIntersection.size() / pUnion.size();
	        }
	        
	        if (!p.isAssigned()) {
		        if (jaccard >= 0.5 && pJaccard >= 0.5 && p.getCompany().getCompanyLocation().equals(studentMapper.findByUsername(applicantUsername).get().getPreferredLocation())) {
		            results.add(p);
		        }
	        }
	    }
	    
	    return results;
	}

}
