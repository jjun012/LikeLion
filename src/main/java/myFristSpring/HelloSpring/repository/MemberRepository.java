package myFristSpring.HelloSpring.repository;

import myFristSpring.HelloSpring.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);

    Member findById(Long id);
    Member findByUserId(String userId);

    List<Member> findAll();

    void deleteMember(Member member);

    public List<Member> findByName(String name);
}
