package trippers.triprecorder.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trippers.triprecorder.entity.User;
import trippers.triprecorder.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@GetMapping(value = "/signup/useridCheck")
	public boolean confirmUserId(@RequestBody User user) {
		return userService.confirmUserId(user);
	}

	@GetMapping(value = "/signup/usernickCheck")
	public boolean confirmNickname(HttpServletRequest request, @RequestBody User user) {
		return userService.confirmNickname(request, user);
	}

	@GetMapping(value = "/signup/useremailCheck")
	public boolean confirmEmail(HttpServletRequest request, @RequestBody User user) {
		return userService.confirmEmail(request, user);
	}

	@PostMapping("/signup")
	public String join(@RequestBody User user) {
		return userService.signUp(user);
	}

	@GetMapping("/findByNick")
	public Long getUserNoByNick(@RequestBody JSONObject obj) {
		return userService.findUserByNickname(obj);
	}

}
