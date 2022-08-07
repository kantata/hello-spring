package hello.hellospring.domain;

import javax.persistence.*;

@Entity //JPA가 관리하는 엔티티
public class Member {

    @Id // pk키 설정
    @GeneratedValue (strategy = GenerationType.IDENTITY)// DB에서 자동 값 생성하기 때문에 identity로 설정
    private Long id; //System이 저장하는 id

    @Column(name = "name") // DB에 있는 컬럼
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
