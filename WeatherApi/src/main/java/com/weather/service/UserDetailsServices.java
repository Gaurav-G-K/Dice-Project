package com.weather.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.weather.entity.Users;
import com.weather.repo.UserRepo;

@Service
public class UserDetailsServices implements UserDetailsService{
	private UserRepo userRepo;
	public UserDetailsServices(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users> user=userRepo.findByEmail(email);
		return user.orElseThrow(()-> new UsernameNotFoundException("No user forund for Email :"+email));
	}
}
