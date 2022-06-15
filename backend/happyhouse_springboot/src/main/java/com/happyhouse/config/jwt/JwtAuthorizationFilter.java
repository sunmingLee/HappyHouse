package com.happyhouse.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.happyhouse.config.auth.PrincipalDetails;
import com.happyhouse.model.UserDto;
import com.happyhouse.model.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

// 시큐리티가 filter를 가지고 있는데 그 필터 중에 BasicAuthenticationFilter 라는 것이 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어 있다.
// 만약 권한 or 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private UserMapper userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	// 인증이나 권한이 필요한 주소요청이 있을 떄 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");

		String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

		// header가 있는지 확인
		if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

		String id = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("id")
				.asString();
		String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken)
				.getClaim("username").asString();
		
		// 서명이 정상적으로 됨
		if (id != null) {
			UserDto userEntity = userRepository.findById(id);

			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

			// JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어 준다.
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
					principalDetails.getAuthorities());

			// 시큐리티를 저장할 수 있는 세션공간(강제로 시큐리티 세션에 접근하여 Authentication 객체를 저장)
			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);
		}
	}
}
