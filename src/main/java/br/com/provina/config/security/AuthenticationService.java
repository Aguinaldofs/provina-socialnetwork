package br.com.provina.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.provina.model.User;
import br.com.provina.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = repository.findByEmail(username);
		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("Invalid data!");
	}

}
