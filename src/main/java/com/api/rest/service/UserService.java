package com.api.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.rest.model.dto.UserDto;
import com.api.rest.model.entity.User;
import com.api.rest.model.form.UserForm;
import com.api.rest.model.form.UserUpdateForm;
import com.api.rest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createUser(UserForm userForm) {
        User user = convertToBusiness(userForm);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public List<UserDto> findAll(){
        List<User> userList = userRepository.findAll();
        return convertListToDto(userList);
    }

    public UserDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return convertToDto(userOptional.get());
        }
        return null;
    }

    public UserDto updateById(UserUpdateForm userForm, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userForm.getName() != null) {
                user.setName(userForm.getName());
            }
            if (userForm.getEmail() != null) {
                user.setEmail(userForm.getEmail());
            }
            userRepository.save(user);
            return convertToDto(user);
        }
        return null;
    }

    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    private User convertToBusiness(UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private List<UserDto> convertListToDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}