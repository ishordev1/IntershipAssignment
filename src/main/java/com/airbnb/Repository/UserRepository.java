package com.airbnb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername(String userName);
}
