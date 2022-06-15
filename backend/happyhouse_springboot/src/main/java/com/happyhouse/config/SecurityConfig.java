package com.happyhouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.happyhouse.config.jwt.JwtAuthenticationFilter;
import com.happyhouse.config.jwt.JwtAuthorizationFilter;
import com.happyhouse.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String TEST_URI = "/**";

	private static final String[] PUBLIC_GET_URI = {"/qna/**" };

	private final CorsConfig corsConfig;
	private final UserMapper userRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//	사용자 정의 필터도 가능
//		http.addFilterBefore(new MyFilter1(), SecurityContextPersistenceFilter.class);
		http.addFilter(corsConfig.corsFilter())	// 인증이 필요한 요청에서의 CORS 설정[필터에 등록]  (@CrossOrigin은 인증이 필요하지 않은 요청에 적용되는 것)
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 x
		
		.and()
		 
		.formLogin().disable()
		.httpBasic().disable()
		
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))	// AuthenticationManager
		.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))	// AuthenticationManager		
		.authorizeRequests()
		.anyRequest().permitAll();
	}

}
