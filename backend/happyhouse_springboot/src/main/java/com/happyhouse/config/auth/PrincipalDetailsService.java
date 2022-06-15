package com.happyhouse.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.happyhouse.model.UserDto;
import com.happyhouse.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailService 타입으로 IoC되어 있는 loadUserByUsername 메서드 실행
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

	private final UserMapper userRepository;
	
	// 해당 방향에 들어감(포함관계)
	// 시큐리티 session <= Authentication <= UserDetails
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		System.out.println("PrincipalDetailsService의 loadUserByUsername");
//		log.info("username : {}",id );
		UserDto userEntity = userRepository.findById(id);

		if (userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
