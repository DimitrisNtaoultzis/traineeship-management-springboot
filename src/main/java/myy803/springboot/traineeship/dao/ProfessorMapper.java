package myy803.springboot.traineeship.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.traineeship.model.Professor;


public interface ProfessorMapper extends JpaRepository<Professor, String>{
	Optional<Professor> findByUsername(String username);
	
}
