package trippers.triprecorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trippers.triprecorder.dto.MultiKeyHeart;
import trippers.triprecorder.entity.Heart;
import trippers.triprecorder.entity.SnsVO;

public interface HeartRepository extends JpaRepository<Heart, MultiKeyHeart> {
	// 특정 게시글에 해당하는 좋아요 리스트 (for. 게시글)
	List<Heart> findBySns(SnsVO sns);
}
