package trippers.triprecorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;
import trippers.triprecorder.dto.MultiKeyHeart;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(MultiKeyHeart.class)
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