package myy803.springboot.traineeship.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum EvaluationType {

	PROFESSOR("PROFESSOR"),
	COMPANY("COMPANY");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final String value;
	
	private EvaluationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
