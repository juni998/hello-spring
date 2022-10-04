package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    public Member save(Member member) {
        member.setId(++sequence); //ID값 세팅, 시스템이 정해줌
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); //Optional로 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        //Map -> ArrayList로 변환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
