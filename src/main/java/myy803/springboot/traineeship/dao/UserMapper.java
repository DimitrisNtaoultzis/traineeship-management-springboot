package myy803.springboot.traineeship.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.traineeship.model.User;



public interface UserMapper extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}
