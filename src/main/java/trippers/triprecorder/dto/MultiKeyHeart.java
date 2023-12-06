package trippers.triprecorder.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MultiKeyHeart implements Serializable{
	// 중복키
	private Long user;
	private Long sns;
}
