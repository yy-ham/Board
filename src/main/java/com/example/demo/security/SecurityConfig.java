package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.csrf((csrf) -> csrf.disable())
 		 	.authorizeHttpRequests((authorizeHttpRequests) ->
 		 		authorizeHttpRequests
	 		 		.requestMatchers("/admin/**").hasRole("ADMIN")
 			 		.requestMatchers("/", "/login", "/signup", 
 			 				"/board/list/**", "/board/detail/**", "/css/**").permitAll()
 			 		.anyRequest().authenticated()
 		 	)
 		 	.formLogin((formLogin) ->
				formLogin
					.usernameParameter("username") //아이디 파라미터명 설정
					.passwordParameter("pwd") //비밀번호 파라미터명 설정
					.loginPage("/login") //사용자 정의 로그인 페이지
					.failureUrl("/loginfail") //로그인 실패 시 이동 페이지
					.defaultSuccessUrl("/board/list/1") //로그인 성공 시 이동 페이지
					.permitAll()
			)
 		 	.logout((logout) ->
				logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.deleteCookies("remove")
					.invalidateHttpSession(true) //로그아웃 시 인증정보 지우고 세션 무효화
					.logoutSuccessUrl("/board/list/1")
			);
 		
 		return http.build();
 	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
