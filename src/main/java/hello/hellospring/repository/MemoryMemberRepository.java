package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ //option+enter

    private static Map<Long, Member> store = new HashMap<>(); //공유되는 변수일경우 동시성문제 때문에 ConcurrentHashMap 써야함
    private static long sequence = 0L; //동시성 AtomicLong

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //널일 가능성이 있어 옵셔널로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) { // 값이 없으면 옵셔널 떄문에 널반환
        return store.values().stream() //loop
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나 찾을때까지 루프 돌림
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
