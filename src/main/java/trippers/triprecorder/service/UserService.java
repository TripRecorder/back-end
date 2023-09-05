package trippers.triprecorder.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import trippers.triprecorder.dto.Role;
import trippers.triprecorder.entity.ProfileVO;
import trippers.triprecorder.entity.User;
import trippers.triprecorder.repository.UserRepository;
import trippers.triprecorder.util.EncodingUtil;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean confirmUserId(User user) {
        boolean result = true;
        String userId = user.getUserId();
        if (!userId.equals("")) {
            User findUser = userRepository.findByUserId(userId);
            result = findUser != null;
        }
        return result;
    }

    public boolean confirmNickname(HttpServletRequest request, User user) {
        String obj = request.getHeader("Authorization");
        User findUser = null;
        boolean result = true;
        String userNick = user.getNickname();

        if (!userNick.equals("")) {
            if (obj != null) {
                Long userNo = EncodingUtil.getUserNo(request);
                findUser = userRepository.findByUserNickAndUserNoNot(userNick, userNo);
            } else {
                findUser = userRepository.findByUserNick(userNick);
            }

            result = findUser != null;
        }

        return result;
    }

    public boolean confirmEmail(HttpServletRequest request, User user) {
        String obj = request.getHeader("Authorization");
        User findUser = null;
        boolean result = true;
        String userEmail = user.getEmail();

        if (!userEmail.equals("")) {
            if (obj != null) {
                Long userNo = EncodingUtil.getUserNo(request);
                findUser = userRepository.findByUserEmailAndUserNoNot(userEmail, userNo);
            } else {
                findUser = userRepository.findByUserEmail(userEmail);
            }
            result = findUser != null;
        }

        return result;
    }

    public String signUp(User user) {
        ProfileVO profile = ProfileVO.builder().build();
        user.setProfile(profile);
        user.setUserPw(EncodingUtil.encodingUserPw(user.getUserPwd()));
        user.setUserRole(Role.ROLE_USER);
        profile.setUser(user);

        userRepository.save(user);
        return "OK";
    }

    public Long findUserByNickname(JSONObject obj) {
        User user = userRepository.findByUserNick(obj.get("nickname").toString());
        return user.getId();
    }

}
