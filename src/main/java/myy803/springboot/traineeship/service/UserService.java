package myy803.springboot.traineeship.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import myy803.springboot.traineeship.model.User;


@Service
public interface UserService extends UserDetailsService{
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public User findById(String username);
}

