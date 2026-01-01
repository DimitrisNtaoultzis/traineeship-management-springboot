package myy803.springboot.traineeship.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="professors")
public class Professor {

	@Id
	@Column(name = "username")
	String username;
	String professorName;
	String interests;
	@OneToMany(mappedBy="supervisor")
	List<TraineeshipPosition> supervisedPositions = new ArrayList<TraineeshipPosition>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public List<TraineeshipPosition> getSupervisedPositions() {
		return supervisedPositions;
	}
	public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
		this.supervisedPositions = supervisedPositions;
	}	
}
