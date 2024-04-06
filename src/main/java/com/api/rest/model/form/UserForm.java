package com.api.rest.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserForm {

    @NotBlank(message = "Name cannot be blank.")
    private String name;
    
    @NotBlank @Email(message = "Invalid email address.")
    private String email;
	
    public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}