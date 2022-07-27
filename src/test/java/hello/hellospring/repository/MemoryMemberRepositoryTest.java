package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트 코드를 돌릴때는 순서가 보장이 안되므로 원하는 결과값이 안나올수 있음. 그래서 테스트가 끝나면 초기화 해줘야함.
    public void afterEach() { // 테스트가 하나 끝날때마다 clear해줌
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); //command+shift+enter

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//option+command+v

        Assertions.assertEquals(member, result); //값 비교
//        Assertions.assertEquals(member, null); //실패

        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); //멤버랑 리절트랑 같은지 비교
        //option+enter : static으로 전환

    }

        @Test
        public void findByName() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member(); //shift+f6 리네임
            member2.setName("spring2");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();//control+option+V

//            org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member2); //result 값과 member2값과 비교=> 객체가 다름
            org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1); //result 값과 member1값과 비교
        }

        @Test
        public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();

            org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
//            org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(3); //error
        }

}
