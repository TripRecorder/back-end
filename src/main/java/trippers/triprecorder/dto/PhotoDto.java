package trippers.triprecorder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import trippers.triprecorder.entity.SnsVO;

@Getter
@Setter
@Builder
public class PhotoDto {
    private Long snsNo;
    private String photo;
}
