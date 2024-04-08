package com.weather.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.entity.RolesAndAuthority;

public interface RoleRepo extends JpaRepository<RolesAndAuthority, Long>{

}
