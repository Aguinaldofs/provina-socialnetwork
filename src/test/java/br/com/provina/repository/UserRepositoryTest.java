package br.com.provina.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.provina.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void shouldReturnEmail() {
		String email = "aguinaldojunior@gec.inatel.br";
		Optional<User> user = userRepository.findByEmail(email);
		Assert.assertEquals(email, user.get().getEmail());
	}
}
