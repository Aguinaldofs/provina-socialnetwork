package br.com.provina.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.provina.model.User;
import br.com.provina.repository.UserRepository;

public class TokenFilterAuthentication extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;

	public TokenFilterAuthentication(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);
		boolean isValid = tokenService.isTokenValid(token);
		if (isValid) {
			authenticateUser(token);
		}

		filterChain.doFilter(request, response);

	}

	private void authenticateUser(String token) {
		Long userId = tokenService.getUserId(token);
		User user = userRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorizatrion");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;

		}
		return token.substring(7, token.length());
	}

}
