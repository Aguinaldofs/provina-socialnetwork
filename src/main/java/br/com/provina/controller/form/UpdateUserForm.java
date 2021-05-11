package br.com.provina.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.provina.model.User;
import br.com.provina.repository.UserRepository;

public class UpdateUserForm {

	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public User editUser(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);

		user.setEmail(email);
		user.setPassword(password);

		return user;

	}

}
