package com.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weather.config.JwtTokenGeneratorFilter;
import com.weather.dto.AuthResponse;
import com.weather.dto.Login;
import com.weather.entity.Users;
import com.weather.repo.UserRepo;

@Service
public class UserServices {
	
	@Autowired
	private UserDetailsServices userDetailsServices;
	@Autowired
	private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	
	public Users AddUser(Users user) {
		
		String encryptedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		Users newUsere=userRepo.save(user);
		return newUsere;
		
	}
	
	public AuthResponse loginUser(Login login) {
      Authentication authentication = authenticate(login.getUsername(), login.getPassword());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtTokenGeneratorFilter.genrateToken(authentication);
      AuthResponse auth=new AuthResponse(token, "Sign In Success");
      return auth; 
  }
	private Authentication authenticate(String username, String password) {
      UserDetails userDetails = userDetailsServices.loadUserByUsername(username);
      if (userDetails == null)
          throw new BadCredentialsException("Invalid Username");
      if (!passwordEncoder.matches(password, userDetails.getPassword()))
          throw new BadCredentialsException("Invalid Password");
      return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

}
