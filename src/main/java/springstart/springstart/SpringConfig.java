package springstart.springstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstart.springstart.MemberRepository.JpaMemberRepository;
import springstart.springstart.MemberRepository.MemberRepository;
import springstart.springstart.MemberRepository.MemoryMemberRepository;
import springstart.springstart.MemberService.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
//    private  DataSource dataSource;
    private  EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    public SpringConfig (DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(entityManager);
    }
}
