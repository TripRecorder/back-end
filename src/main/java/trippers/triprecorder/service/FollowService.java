package trippers.triprecorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import trippers.triprecorder.dto.UserSimpleDto;
import trippers.triprecorder.entity.Follow;
import trippers.triprecorder.entity.UserVO;
import trippers.triprecorder.repository.FollowRepository;
import trippers.triprecorder.repository.UserRepository;
import trippers.triprecorder.util.AwsUtil;
import trippers.triprecorder.util.EncodingUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    // 팔로우 등록 및 취소
    public boolean postFollow(Long followerNo, Long userNo) {
        boolean result = true;

        UserVO follower = userRepository.findById(followerNo).orElse(null);
        UserVO following = userRepository.findById(userNo).orElse(null);

        Follow follow = followRepository.findByFollowerAndFollowing(follower, following);

        if (follow == null) {
            Follow newFollow = Follow.builder().follower(follower).following(following).build();
            followRepository.save(newFollow);
        } else {
            followRepository.delete(follow);
            result = false;
        }
        return result;
    }

    // 사용자의 팔로워 리스트
    public List<UserSimpleDto> getFollowerList(Long userNo) {
        UserVO tmpFollowing = userRepository.findById(userNo).orElse(null); //내 정보
        List<Follow> tmpFollowingList = followRepository.findByFollowing(tmpFollowing); //나를 팔로잉하는 팔로우 정보

        List<UserSimpleDto> followingList = new ArrayList<>();

        tmpFollowingList.forEach(following -> {
            UserVO tmpUser = following.getFollower();
            UserSimpleDto user = UserSimpleDto.builder().userNo(tmpUser.getUserNo()).userNick(tmpUser.getUserNick())
                    .userId(tmpUser.getUserId())
                    .userProfile(AwsUtil.getImageURL(tmpUser.getProfile().getProfilePhoto())).build();
            followingList.add(user);
        });

        return followingList;
    }

    // 사용자의 팔로잉 리스트
    public List<UserSimpleDto> getFollowingList(Long userNo) {
        UserVO tmpFollower = userRepository.findById(userNo).orElse(null);
        List<Follow> tmpFollowerList = followRepository.findByFollower(tmpFollower);

        List<UserSimpleDto> followerList = new ArrayList<>();

        tmpFollowerList.forEach(follower -> {
            UserVO tmpUser = follower.getFollowing();
            UserSimpleDto user = UserSimpleDto.builder().userNo(tmpUser.getUserNo()).userNick(tmpUser.getUserNick())
                    .userId(tmpUser.getUserId())
                    .userProfile(AwsUtil.getImageURL(tmpUser.getProfile().getProfilePhoto())).build();
            followerList.add(user);
        });

        return followerList;
    }
}
