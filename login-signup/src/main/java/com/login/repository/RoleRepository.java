package com.login.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.login.models.ERole;
import com.login.models.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	Optional<Role> findByName(ERole name);

}
