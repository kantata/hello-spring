package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너가 생성할때 멤버컨트롤러 객체가 생성되서 넣어둠 : Bean 관리
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private MemberService memberService;


    @Autowired //스프링컨테이너에서 memberService를 가져옴
    public MemberController(MemberService memberService) { //생성자
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 1.
    public String createForm() {
        return "members/createMemberForm"; // 2.
    }

    @PostMapping("/members/new") // 4.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
