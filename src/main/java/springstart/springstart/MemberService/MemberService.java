package springstart.springstart.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import springstart.springstart.MemberRepository.MemberRepository;
import springstart.springstart.domain.Member;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        // 중복회원 검증
        isExistMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    private void isExistMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(repositoryName -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원조회
    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    // 단일 회원 조회
    public Optional<Member> findMemberById (Long memberId) {
        return memberRepository.findById(memberId);
    }
}
