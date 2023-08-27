package trippers.triprecorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import trippers.triprecorder.dto.MultiKeyHeart;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "heart")
@IdClass(MultiKeyHeart.class)
@ToString(exclude = { "user", "sns" })
public class Heart {
	@Id
	@ManyToOne
	@JoinColumn(name = "user_no")
	private UserVO user;
	@Id
	@ManyToOne
	@JoinColumn(name = "sns_no")
	private SnsVO sns;
}