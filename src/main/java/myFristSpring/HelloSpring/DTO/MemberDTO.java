package myFristSpring.HelloSpring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class MemberDTO {
    @Data
    public static class MemberCreateReq{
        private String userId;
        private String password;
        private String nickname;
    }
    @Data
    public static class LoginReq{
        private String userId;
        private String password;
    }

    @Data
    public static class MemberUpdateReq{
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    public static class MemberRes {
        private String userId;
        private String nickname;
    }
    @Data
    public static class MemberDeleteReq{
        private String userId;
    }
}
