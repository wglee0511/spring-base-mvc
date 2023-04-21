package springstart.springstart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springstart.springstart.MemberService.MemberService;
import springstart.springstart.domain.Member;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join((member));
        return "redirect:/";
    }

    @GetMapping("/members")
    public String viewMemberList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        model.addAttribute("members", members);3

        return "members/memberList";
    }
}
