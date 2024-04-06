package com.api.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import com.api.rest.model.entity.User;
import com.api.rest.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestApiApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}
	
	@BeforeEach
	void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void testFindAll() {
		User user = getUser();
		userRepository.save(user);
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(u -> users.add(u));
		assertEquals(users.size(), 1);
	}

	private User getUser() {
		User user = new User();
		user.setId(1L);
		user.setName("name1");
		user.setEmail("email1@email.com");
		return user;
	}
}