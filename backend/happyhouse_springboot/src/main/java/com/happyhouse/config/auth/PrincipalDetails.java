package com.happyhouse.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.happyhouse.model.UserDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 Security session을 만들어 준다. (Security ContextHolder)
// 오브젝트 타입 => Authentication 타입의 객체
// Authentication 안에 User정보가 있어야 함!
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session 에 저장 가능한 객체가 Authentication(정해짐) 
// 그리고 Authentication 객체 안에는 유저 정보가 저장되어 (UserDetails 타입)

// Security Session => Authentication => UserDetails(PrincipalDetails) => 유저 오브젝트
@Slf4j
@Data
public class PrincipalDetails implements UserDetails {

	private UserDto userDto; // 콤포지션

	public PrincipalDetails(UserDto userDto) {
		this.userDto = userDto;
//		log.info("userDto : {}", this.userDto);
	}

	// 해당 User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		userDto.getRoleList().forEach((r -> {
			authorities.add(() -> r);
		}));
		return authorities;
	}

	@Override
	public String getPassword() {

		return userDto.getUser_password();
	}

	@Override
	public String getUsername() {
		return userDto.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화
	@Override
	public boolean isEnabled() {

		// 예) 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로 함
		// 현재시간 - 마지막 로그인 시간 => 1년 초과시 return false;
		return true;
	}

}
