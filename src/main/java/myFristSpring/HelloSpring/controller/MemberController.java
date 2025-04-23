package myFristSpring.HelloSpring.controller;

import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.DTO.MemberDTO;
import myFristSpring.HelloSpring.domain.Member;
import myFristSpring.HelloSpring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/add")
    public String addMember(@RequestBody MemberDTO.MemberCreateReq request) {
        Member member =memberService.signUp(request.getUserId(),request.getPassword(),request.getNickname());
        if (member==null) {
            return null;
        }
        return memberService.login(member.getUserId(),member.getPassword());

    }

}
