package com.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.model.dto.UserDto;
import com.api.rest.model.form.UserForm;
import com.api.rest.model.form.UserUpdateForm;
import com.api.rest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid UserForm userForm) {
        return userService.createUser(userForm);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateById(@RequestBody UserUpdateForm form, @PathVariable("id") Long id) {
        return userService.updateById(form, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
       userService.deleteById(id);
    }
}