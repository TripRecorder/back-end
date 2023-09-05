package trippers.triprecorder.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trippers.triprecorder.dto.Role;
import trippers.triprecorder.entity.ProfileVO;
import trippers.triprecorder.entity.User;
import trippers.triprecorder.repository.UserRepository;
import trippers.triprecorder.util.EncodingUtil;

// 회원
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository urepo; //userRepository

	// 회원가입 - 아이디 중복 체크
	// 중복이다 - true, 중복이 아니다 - false
	@PostMapping(value = "/signup/useridCheck") //get으로 변경
	public boolean postUseridCheck(@RequestBody User user) { // checkUserId
		boolean result = true;
		String userId = user.getUserId(); // 처음부터 파라미터를 id로 받기
		if (!userId.equals("")) {
			User findUser = urepo.findByUserId(userId);
			result = findUser != null;
		}
		return result;
	}

	// 회원가입 - 닉네임 중복 체크
	// 중복이다 - true, 중복이 아니다 - false
	@PostMapping(value = "/signup/usernickCheck")
	public boolean postUsernickCheck(HttpServletRequest request, @RequestBody User user) {
		String obj = request.getHeader("Authorization"); // ???
		User findUser = null;
		boolean result = true;
		String userNick = user.getUserNick();

		if (!userNick.equals("")) {
			if (obj != null) {
				Long userNo = EncodingUtil.getUserNo(request);
				findUser = urepo.findByUserNickAndUserNoNot(userNick, userNo);
			} else {
				findUser = urepo.findByUserNick(userNick);
			}

			result = findUser != null;
		}

		return result;
	}

	// 회원가입 - 이메일 중복 체크
	// 중복이다 - true, 중복이 아니다 - false
	@PostMapping(value = "/signup/useremailCheck")
	public boolean postUseremailCheck(HttpServletRequest request, @RequestBody User user) {
		String obj = request.getHeader("Authorization");
		User findUser = null;
		boolean result = true;
		String userEmail = user.getUserEmail();

		if (!userEmail.equals("")) {
			if (obj != null) {
				Long userNo = EncodingUtil.getUserNo(request);
				findUser = urepo.findByUserEmailAndUserNoNot(userEmail, userNo);
			} else {
				findUser = urepo.findByUserEmail(userEmail);
			}
			result = findUser != null;
		}

		return result;
	}

	// 회원가입
	@PostMapping("/signup")
	public String join(@RequestBody User user) {
		ProfileVO profile = ProfileVO.builder().build();
		user.setProfile(profile);
		user.setUserPw(EncodingUtil.encodingUserPw(user.getUserPw()));
		user.setUserRole(Role.ROLE_USER);
		profile.setUser(user);

		urepo.save(user);
		return "OK";
	}

	// 닉네임으로 유저 찾기
	@PostMapping("/findByNick")
	public Long getUserNoByNick(@RequestBody JSONObject obj) {
		User user = urepo.findByUserNick(obj.get("nickname").toString());
		return user.getUserNo();
	}
}
