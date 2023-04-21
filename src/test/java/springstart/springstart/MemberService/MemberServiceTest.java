package springstart.springstart.MemberService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import springstart.springstart.MemberRepository.MemoryMemberRepository;
import springstart.springstart.domain.Member;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void  afterEach () {

        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("테스트_회원_1");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memberService.findMemberById(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원가입_검증() {
        //given
        Member member1 = new Member();
        member1.setName("회원_1");

        Member member2 = new Member();
        member2.setName("회원_2");

        //when
        memberService.join(member1);
        IllegalStateException error = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        //then
        assertThat(error.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        Member member = new Member();
        member.setName("member");
        Member member1 = new Member();
        member1.setName("member1");

        memberService.join(member);
        memberService.join(member1);

        List<Member> findAllMembers = memberService.findMembers();

        assertThat(findAllMembers.toArray().length).isEqualTo(2);
    }

    @Test
    void findMemberById() {
        Member member = new Member();
        Member member1 = new Member();
        member.setId(1L);
        member.setName("회원_1");
        member1.setId(2L);
        member1.setName("회원_2");

        memberService.join(member);
        Long member1Id = memberService.join(member1);
        assertThat(member1Id).isEqualTo(2L);

        Optional<Member> foundMember = memberService.findMemberById(2L);
        assertThat(foundMember).isEqualTo(Optional.ofNullable(member1));
    }
}