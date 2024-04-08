package com.weather.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.entity.Users;


public interface UserRepo extends JpaRepository<Users, Long>{

	Optional<Users> findByEmail(String email);
}
