package com.happyhouse.config.jwt;

public interface JwtProperties {
	String SECRET = "배인수";
	int EXPIRATION_TIME = 864_000_000; // 10분 60_000*10 | 10일 (단위 1/1000초) 864_000_000
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
