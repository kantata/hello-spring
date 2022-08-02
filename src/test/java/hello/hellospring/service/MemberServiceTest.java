package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
    MemberService memberService;

    //테스트 코드에서 사용하는 MemoryMemberRepository가 실제 MemberService에서 사용하는 MemoryMemberRepository랑 다른 인스턴스기 때문에 개선 필요, 현재는 MemoryMemberRepository.store가 static으로 선언되어 있어서 이슈 없음
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 테스트 코드를 돌릴때는 순서가 보장이 안되므로 원하는 결과값이 안나올수 있음. 그래서 테스트가 끝나면 초기화 해줘야함.
    public void afterEach() { // 테스트가 하나 끝날때마다 clear해줌
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given : 상황이 주어짐
        Member member = new Member();
        member.setName("hello");

        //when : 이럴때
        Long saveId = memberService.join(member);

        //then : 결과가 나와야
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // option+enter = move to static

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member1.setName("spring");

        //when
        memberService.join(member1);
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123"); fail
        }
*/
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));//() -> memberService.join(member2) 로직을 실행시 IllegalStateException.class이 나와야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));//() -> memberService.join(member2) 로직을 실행시 IllegalStateException.class이 나와야함
        assertThat(e.getMessage()).isEqualTo("이미 존재 하는 회원입니다.");


        //then
    }
}