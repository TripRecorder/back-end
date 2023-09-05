package trippers.triprecorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trippers.triprecorder.entity.FollowVO;
import trippers.triprecorder.entity.User;

public interface FollowRepository extends JpaRepository<FollowVO, Long> {
	// 팔로우가 어떤 사람이면서 팔로잉이 어떤 사람 (for. 팔로우 여부 확인)
	FollowVO findByFollowerAndFollowing(User follower, User following);

	// 팔로워 찾기 (for. 팔로잉 리스트 구하기)
	List<FollowVO> findByFollower(User follower);

	// 팔로잉 찾기 (for. 팔로워 리스트 구하기)
	List<FollowVO> findByFollowing(User following);
}
