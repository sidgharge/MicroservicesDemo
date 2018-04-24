package com.bridgelabz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}