package trippers.triprecorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trippers.triprecorder.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// 회원가입 시 아이디 중복 체크
	public User findByUserId(String userid);

	// 회원가입 시 닉네임 중복 체크
	public User findByUserNick(String usernick);

	// 내 정보 수정 시 닉네임 중복 체크
	public User findByUserNickAndUserNoNot(String usernick, Long userNo);

	// 회원가입 시 이메일 중복 체크
	public User findByUserEmail(String useremail);

	// 내 정보 수정 시 닉네임 중복 체크
	public User findByUserEmailAndUserNoNot(String useremail, Long userNo);

	// 해당 단어를 포함하는 닉네임을 가진 사용자 (for. 닉네임 검색)
	public List<User> findByUserNickContaining(String usernick);
}