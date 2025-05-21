package myFristSpring.HelloSpring.repository;

import myFristSpring.HelloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> local = new HashMap<>();
    private static long sequence=0L;

    @Override
    public Member save(Member member) {
        member.setId(sequence++);
        local.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Member findByUserId(String userId) {
        for (Member member : local.values()) {
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public void deleteMember(Member member) {
        local.remove(member.getId());
    }

    @Override
    public List<Member> findByName(String name) {
        return List.of();
    }
}
