package springstart.springstart.MemberService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springstart.springstart.domain.Member;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;

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
}
