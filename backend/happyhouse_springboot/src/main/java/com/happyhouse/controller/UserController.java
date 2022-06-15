package com.happyhouse.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.AuthRequest;
import com.happyhouse.model.UserDto;
import com.happyhouse.model.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

//	@Autowired
//	private JwtUtil jwtUtil;

//	@Autowired
//	private AuthenticationManager authenticationManager;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getId(), authRequest.getUser_password()));
//		} catch (Exception ex) {
//			throw new Exception("inavalid id/password");
//		}
//		return jwtUtil.generateToken(authRequest.getId());
//	}

//	@GetMapping("/register")
//	public String register() {
//		return "user/register";
//	}
//
//	@GetMapping("/info")
//	public String info() {
//		return "user/user_info";
//	}

	@GetMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		log.debug("id:{}", checkId);
		int idCount = userService.idCheck(checkId);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		log.debug("idCount:{}", idCount);
		return json.toString();
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
		log.debug("userDto info: {}", userDto);

		// 유저의 권한
		userDto.setRoles("ROLE_USER");
		// 암호화 안하면 시큐리티로 로그인을 할 수 없음
		String rawPassword = userDto.getUser_password();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);

		userDto.setUser_password(encPassword);
		int registerUser = userService.registerUser(userDto);

		if (registerUser == 1) {
			return new ResponseEntity<Integer>(registerUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Integer>(registerUser, HttpStatus.BAD_REQUEST);
		}
	}

//	@PostMapping("/login")
//	public String login(@RequestParam AuthRequest authRequest) throws Exception {
//		log.debug("userId : {}", authRequest);
//		
//		String rawPassword = authRequest.getUser_password();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//		authRequest.setUser_password(encPassword);
////		MemberDto memberDto = memberService.login(map);
////		UserDto userDto = userService.login(map);
//		
//		return "";
//	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/info/{userid}")
	public UserDto getInfo(@PathVariable String userid) throws Exception {
		log.debug("사용자 정보");
		UserDto userDto = userService.findById(userid);
		userDto.setUser_password(null);
		return userDto;
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserDto userDto) throws Exception {
		log.debug("정보 수정 = {}", userDto);
		int updateUser = userService.updateUser(userDto);

		if (updateUser == 1) {
			return new ResponseEntity<Integer>(updateUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(updateUser, HttpStatus.BAD_REQUEST);
		}
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@DeleteMapping("/{userid}")
	public String delete(@PathVariable("userid") String id, HttpSession session) throws Exception {
		log.debug("삭제할 아이디 : {}", id);
		userService.deleteUser(id);
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/login/success")
	public void loginSuccess() {
		log.debug("로그인 성공");
	}

	@GetMapping("/login/fail")
	public void loginFail() {
		log.debug("로그인 실패");
	}
}
