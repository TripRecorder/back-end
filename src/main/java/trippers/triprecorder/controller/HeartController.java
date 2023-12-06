package trippers.triprecorder.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trippers.triprecorder.service.HeartService;
import trippers.triprecorder.util.EncodingUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

	private final HeartService heartService;

	// 좋아요 등록 및 취소
	@PostMapping("/register/{snsNo}")
	public boolean postHeart(HttpServletRequest request, @PathVariable Long snsNo) {
		Long userNo = EncodingUtil.getUserNo(request);
		return heartService.postHeart(userNo, snsNo);
	}
}
