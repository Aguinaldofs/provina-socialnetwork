package br.com.provina.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.provina.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private AuthenticationService authenticationService;
	private UserRepository userRepository;
	private TokenService tokenService;

	@Autowired
	public SecurityConfigurations(AuthenticationService authenticationService, UserRepository userRepository,
			TokenService tokenService) {
		this.authenticationService = authenticationService;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Authentication configuration
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());

	}

	// Authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll().antMatchers("/auth")
				.permitAll().antMatchers("/h2-console/**").permitAll().antMatchers("/auth/**").permitAll().anyRequest()
				.authenticated().and().csrf().ignoringAntMatchers("/h2-console/**").disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new TokenFilterAuthentication(tokenService, userRepository),
						UsernamePasswordAuthenticationFilter.class);

	}

	// Static resources configurations(css, js, images, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("*");
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsFilter(source);
	}
}
