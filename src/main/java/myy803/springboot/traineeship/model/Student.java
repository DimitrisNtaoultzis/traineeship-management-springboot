package myy803.springboot.traineeship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

	@Id
	@Column(name = "username")
	String username;
	String studentName;
	String AM;
	double avgGrade;
	String preferredLocation;
	String interests;
	String skills;
	boolean lookingForTraineeship = false;
	@OneToOne
    @JoinColumn(name = "traineeship_position_id", referencedColumnName = "id")
	TraineeshipPosition assignedPosition;
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getAM() {
		return AM;
	}
	public void setAM(String aM) {
		AM = aM;
	}
	public double getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public boolean isLookingForTraineeship() {
		return lookingForTraineeship;
	}
	public void setLookingForTraineeship(boolean lookingForTraineeship) {
		this.lookingForTraineeship = lookingForTraineeship;
	}
	public TraineeshipPosition getAssignedPosition() {
		return assignedPosition;
	}
	public void setAssignedPosition(TraineeshipPosition assignedPosition) {
		this.assignedPosition = assignedPosition;
	}
}
