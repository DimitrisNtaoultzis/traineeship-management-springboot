package myy803.springboot.traineeship.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="companies")
public class Company {
	
	@Id
    @Column(name = "username")
    private String username; 

	String companyName;
	String companyLocation;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public List<TraineeshipPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<TraineeshipPosition> positions) {
		this.positions = positions;
	}
}
