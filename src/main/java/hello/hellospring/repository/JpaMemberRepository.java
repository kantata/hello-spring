package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //PK키 조회 있을때 조회 방법
        Member member = em.find(Member.class, id); //select : find(조화할타입, seq값) => pk값이 있을경
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { //PK키 없을때 조회 방법
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) //Member.class 로 조회
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //PK키 없을때 조회 방법
        List<Member> result = em.createQuery("select m from Member m", Member.class) //jpql 쿼리, 객체를 대상으로 쿼리를 날림
                .getResultList(); //command + option + n
        return result;
    }
}
