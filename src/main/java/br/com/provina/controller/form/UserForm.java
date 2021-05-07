package br.com.provina.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.provina.model.User;

public class UserForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public User convert() {
		String encryptedPassword = new BCryptPasswordEncoder().encode(password);
		return new User(name, email, encryptedPassword);
	}

}
