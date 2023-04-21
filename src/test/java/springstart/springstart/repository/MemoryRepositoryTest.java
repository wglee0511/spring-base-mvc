package springstart.springstart.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springstart.springstart.MemberRepository.MemoryMemberRepository;
import springstart.springstart.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryRepositoryTest{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach () {
        repository.clearStore();
    }

    @Test
    public void save () {
        Member member = new Member();
        member.setName("test1");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById () {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findById(member1.getId()).get();
        assertThat(result).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findByName("test2").get();

        assertThat(result).isEqualTo(member2);
    }
    @Test
    public void findAll () {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
