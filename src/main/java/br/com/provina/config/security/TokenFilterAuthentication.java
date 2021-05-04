package br.com.provina.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TokenFilterAuthentication extends OncePerRequestFilter {

	private TokenService tokenService;

	public TokenFilterAuthentication(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);
		boolean valid = tokenService.isTokenValid(token);
		System.out.println(valid);

		filterChain.doFilter(request, response);

	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorizatrion");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;

		}
		return token.substring(7, token.length());
	}

}
