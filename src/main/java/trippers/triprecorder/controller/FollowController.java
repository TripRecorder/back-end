package trippers.triprecorder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trippers.triprecorder.dto.UserSimpleDto;
import trippers.triprecorder.entity.Follow;
import trippers.triprecorder.entity.UserVO;
import trippers.triprecorder.repository.FollowRepository;
import trippers.triprecorder.repository.UserRepository;
import trippers.triprecorder.service.FollowService;
import trippers.triprecorder.util.AwsUtil;
import trippers.triprecorder.util.EncodingUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

	private final FollowService followService;

	// 팔로우 등록 및 취소
	@PostMapping("/register/{userNo}")
	public boolean postFollow(HttpServletRequest request, @PathVariable Long userNo) {
		Long followerNo = EncodingUtil.getUserNo(request);

		return followService.postFollow(followerNo, userNo);
	}

	// 사용자의 팔로워 리스트
	@GetMapping("/{userNo}/follower/list")
	public List<UserSimpleDto> getFollowerList(@PathVariable Long userNo) {
		return followService.getFollowerList(userNo);
	}

	// 사용자의 팔로잉 리스트
	@GetMapping("/{userNo}/following/list")
	public List<UserSimpleDto> getFollowingList(@PathVariable Long userNo) {
		return followService.getFollowingList(userNo);
	}
}
