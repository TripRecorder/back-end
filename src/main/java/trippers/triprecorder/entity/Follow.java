package trippers.triprecorder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = { "follower", "following" })
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long followNo;

	// 팔로워 (나를 follow한 사람)
	@ManyToOne
	@JoinColumn(name = "follower")
	private UserVO follower;

	// 팔로잉 (내가 follow한 사람)
	@ManyToOne
	@JoinColumn(name = "following")
	private UserVO following;

}