package br.com.provina.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TokenFilterAuthentication extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);
		System.out.println(token);

		filterChain.doFilter(request, response);

	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorizatrion");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {

		}
		return token.substring(7, token.length());
	}

}
