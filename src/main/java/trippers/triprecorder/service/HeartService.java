package trippers.triprecorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trippers.triprecorder.dto.MultiKeyHeart;
import trippers.triprecorder.entity.Heart;
import trippers.triprecorder.repository.HeartRepository;
import trippers.triprecorder.repository.SnsRepository;
import trippers.triprecorder.repository.UserRepository;
import trippers.triprecorder.util.EncodingUtil;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final SnsRepository snsRepository;

    // 좋아요 등록 및 취소
    public boolean postHeart(Long userNo, Long snsNo){
        
        MultiKeyHeart key = MultiKeyHeart.builder().user(userNo).sns(snsNo).build();
        Heart heart = heartRepository.findById(key).orElse(null);

        boolean result = true;

        if (heart == null) { //하트 등록
            Heart newHeart = Heart.builder().user(userRepository.findById(userNo).orElse(null))
                    .sns(snsRepository.findById(snsNo).orElse(null)).build();
            heartRepository.save(newHeart);
        } else { //하트 취소
            heartRepository.delete(heart);
            result = false;
        }

        return result;
    }
}
