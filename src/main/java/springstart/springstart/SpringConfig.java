package springstart.springstart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstart.springstart.MemberRepository.MemberRepository;
import springstart.springstart.MemberRepository.MemoryMemberRepository;
import springstart.springstart.MemberService.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
