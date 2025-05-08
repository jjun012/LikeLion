package myFristSpring.HelloSpring.controller;

import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.DTO.MemberDTO;
import myFristSpring.HelloSpring.domain.Member;
import myFristSpring.HelloSpring.security.JwtUtility;
import myFristSpring.HelloSpring.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/add")
    public String addMember(@RequestBody MemberDTO.MemberCreateReq request) {
        Member member = memberService.signUp(request.getUserId(), request.getPassword(), request.getNickname());
        if (member == null) {
            return null;
        }
        return memberService.login(request.getUserId(), request.getPassword());

    }

    @PostMapping("/member/login")
    public String login(@RequestBody MemberDTO.LoginReq request) {
        return memberService.login(request.getUserId(), request.getPassword());
    }

    @GetMapping("/member/{userId}")
    public MemberDTO.MemberRes getMember(@PathVariable("userId") String userId) {
        Member member = memberService.findByUserId(userId);
        return new MemberDTO.MemberRes(member.getUserId(), member.getNickname());
    }

    private final JwtUtility jwtUtility;

    @PutMapping("/member")
    public MemberDTO.MemberRes changeMemberName(@RequestHeader("Authorization") String token, @RequestBody MemberDTO.MemberUpdateReq request) {
        if (!jwtUtility.validateToken(token)) {
            return null;
        }
        Member findMember = memberService.changeName(token, request.getNickname());
        return new MemberDTO.MemberRes(findMember.getUserId(), findMember.getNickname());
    }

    @DeleteMapping("/member")
    public Boolean deleteMember(@RequestHeader("Authorization") String token, @RequestBody MemberDTO.MemberDeleteReq request) {
        if (!jwtUtility.validateToken(token)) {
            return false;
        }
        return memberService.deleteMember(request.getUserId());
    }

}

