package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너가 생성할때 멤버컨트롤러 객체가 생성되서 넣어둠 : Bean 관리
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired //스프링컨테이너에서 memberService를 가져옴
    public MemberController(MemberService memberService) { //생성자
        this.memberService = memberService;
    }
}
