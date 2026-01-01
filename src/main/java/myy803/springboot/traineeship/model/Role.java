package myy803.springboot.traineeship.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum Role {
	STUDENT("Student"),
	COMPANY("Company"),
	COMMITEE("Commitee"),
    SUPERVISOR("Supervisor");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}