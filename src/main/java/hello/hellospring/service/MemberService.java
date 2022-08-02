package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { // 비즈니스 로직
    
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //command+n
    public MemberService(MemberRepository memberRepository) { //외부에서 넣어주게 바꿈 : DI(dependency injection)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        /*
            Optional<Member> result = memberRepository.findByName(member.getName()); //옵셔널 안에 멤버가 있음 널이 있음 옵셔널로 하기
            //result.orElseGet(); 값이 있으면 꺼내고 아니면 디폴트로 쓰기
            result.ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
         */
        validateDuplicateMember(member); //control + m = 8

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //option + command + shift + L
               .ifPresent(member1 -> {
                   throw new IllegalStateException("이미 존재 하는 회원입니다."); //control + t
               });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
