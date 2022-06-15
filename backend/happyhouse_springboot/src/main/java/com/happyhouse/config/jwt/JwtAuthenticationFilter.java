package com.happyhouse.config.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happyhouse.config.auth.PrincipalDetails;
import com.happyhouse.model.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password 전송하면(post)
// UsernamePasswordAuthenticationFilter 동작을 함
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
//		System.out.println("JwtAuthenticationFilter:로그인 시도중");

		// 1.id, password 받아서

		ObjectMapper om = new ObjectMapper();

		UserDto userDto = null;
		try {
			userDto = om.readValue(request.getInputStream(), UserDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDto.getId(), userDto.getUser_password());

		// 2. 정상인지 로그인 시도. authenticationManager로 로그인 시도를 하면
		// PrincilpalDetailsService가 호출 loadUserByUsername() 메서드 실행됨.(DB에 있는 id와
		// password 일치)
		// 정상이면 authentication 리턴 아니먄 null
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		// authentication 객체가 session영역에 저장됨
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

//		System.out.println(principalDetails.getUserDto().getId());

		// 3. PrincipalDetails를 세션에 담고(이게 있어야 권한 관리가 됨)
		// 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 한다.
		// 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 권한 처리를 위해 session에 넣는 것.

		return authentication;

	}

	// 4. JWT 토큰을 만들어서 응답해주면 된다.
	// attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 메서드 실행
	// 여기서 JWT 토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response해주면 됨.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
//		System.out.println("successfulAuthentication: 사용자 인증됐다는 뜻");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		// Hash암호
		String jwtToken = JWT.create().withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.withClaim("id", principalDetails.getUserDto().getId())
				.withClaim("username", principalDetails.getUserDto().getUser_name())
				.withClaim("email", principalDetails.getUserDto().getUser_email())
				.withClaim("tel", principalDetails.getUserDto().getUser_tel())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("access-token", jwtToken);
        body.put("code", HttpStatus.OK.value());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), body);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.error("unsuccessfulAuthentication failed.getLocalizedMessage(): {}", failed.getLocalizedMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.UNAUTHORIZED.value());
        body.put("error", failed.getMessage());

        new ObjectMapper().writeValue(response.getOutputStream(), body);
	}
	
	
}
