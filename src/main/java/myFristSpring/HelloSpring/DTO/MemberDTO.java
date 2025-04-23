package myFristSpring.HelloSpring.DTO;

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
}
