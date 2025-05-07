package myFristSpring.HelloSpring.service;

import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.domain.Member;
import myFristSpring.HelloSpring.repository.MemberRepository;
import myFristSpring.HelloSpring.security.JwtUtility;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    public Member signUp(String userId, String password, String nickname) {
        if (memberRepository.findByUserId(userId) != null) {
            return null;
        }
        Member member = new Member(userId, password);
        member.setNickname(nickname);
        memberRepository.save(member);
        return member;
    }
    public String login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);

        if(member == null || !member.checkPassword(password)) {
            return null;
        }
        return jwtUtility.generateToken(member.getUserId());
    }
    public Member tokenToMember(String token) {
        return memberRepository.findByUserId(jwtUtility.getClaimsFromToken(token).getSubject());
    }
    public Member changeName(String token, String newNickname) {
        Member member = memberRepository.findByUserId(token);
        if (member == null) {
            return null;
        }
        member.setNickname(newNickname);
        return member;
    }

    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public boolean deleteMember(String userId) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) {
            return false;
        }
        memberRepository.deleteMember(member);
        return true;
    }
}
