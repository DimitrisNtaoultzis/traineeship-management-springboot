package myy803.springboot.traineeship.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.traineeship.model.Company;


public interface CompanyMapper extends JpaRepository<Company, String>{

	Optional<Company> findByUsername(String username);
}
