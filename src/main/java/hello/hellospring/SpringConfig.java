package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //인터페이스는 new 안됨
//        return new MemoryMemberRepository(); // Memory 구현체
//        return new JdbcMemberRepository(dataSource); // JDBC 구현체
//        return new JdbcTemplateMemberRepository(dataSource); // JDBCTemplate 구현체
        return new JpaMemberRepository(em); // JPA 구현체
    }
}
