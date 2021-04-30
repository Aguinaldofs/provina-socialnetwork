package br.com.provina.controller.dto;

import br.com.provina.model.User;

public class UserDto {
	private String name;
	private String email;

	public UserDto(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

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
