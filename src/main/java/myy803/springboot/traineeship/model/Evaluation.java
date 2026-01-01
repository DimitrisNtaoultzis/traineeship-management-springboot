package myy803.springboot.traineeship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="evaluations")
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Enumerated(EnumType.STRING)
    @Column(name="type")
	EvaluationType evaluationType;
	int motivation;
	int efficiency;
	int effectiveness;
	int facilities;
	int guidance;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EvaluationType getEvaluationType() {
		return evaluationType;
	}
	public void setEvaluationType(EvaluationType evaluationType) {
		this.evaluationType = evaluationType;
	}
	public int getMotivation() {
		return motivation;
	}
	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}
	public int getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	public int getEffectiveness() {
		return effectiveness;
	}
	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}
	public int getFacilities() {
		return facilities;
	}
	public void setFacilities(int facilities) {
		this.facilities = facilities;
	}
	public int getGuidance() {
		return guidance;
	}
	public void setGuidance(int guidance) {
		this.guidance = guidance;
	}
	
}
