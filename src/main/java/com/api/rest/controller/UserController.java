package com.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.model.dto.UserDto;
import com.api.rest.model.form.UserForm;
import com.api.rest.model.form.UserUpdateForm;
import com.api.rest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserForm userForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userForm));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
    	
    	List<UserDto> userList = userService.findAll();
    	
    	if(userList.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
        
    	return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        
    	Optional<UserDto> userDtoOptional = userService.findById(id);
    	
    	if (userDtoOptional.isPresent()) {
    		return ResponseEntity.ok(userDtoOptional.get());
    	}

    	return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateById(@RequestBody UserUpdateForm form, @PathVariable("id") Long id) {
        
    	Optional<UserDto> userDtoOptional = userService.updateById(form, id);
    	
    	if (userDtoOptional.isPresent()) {
    		return ResponseEntity.ok(userDtoOptional.get());
    	}

    	return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
       
    	boolean deleted = userService.deleteById(id);
    	
    	if(deleted) {
    		return ResponseEntity.noContent().build();
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
}