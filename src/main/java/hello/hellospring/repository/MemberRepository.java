package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional -> 값이 널일수도 있을때 예외 처리
    Optional<Member> findByName(String name);
     List<Member> findAll();
}
