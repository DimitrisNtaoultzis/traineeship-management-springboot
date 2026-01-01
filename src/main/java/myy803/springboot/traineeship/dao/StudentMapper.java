package myy803.springboot.traineeship.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.traineeship.model.Student;



public interface StudentMapper extends JpaRepository<Student, String>{
	Optional<Student> findByUsername(String username);
}
