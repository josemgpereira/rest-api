package com.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}