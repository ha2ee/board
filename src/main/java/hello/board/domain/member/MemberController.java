package hello.board.domain.member;

import hello.board.validation.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    //로그인 폼 요청
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm form) {
        return "page/member/loginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            return "page/member/loginForm";
        }

        MemberResponse loginMember = memberService.login(form.getLoginId(), form.getPassword());

        log.info("loginMember={}", loginMember);

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "page/member/loginForm";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//        session.setMaxInactiveInterval(60 * 30);
        log.info("session id={}", SessionConst.LOGIN_MEMBER);

        String message = "로그인 되었습니다.";
        redirectMessage(redirectAttributes,message);
        return "redirect:" + redirectURL;
    }

    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        String message = "로그아웃 되었습니다.";
        redirectMessage(redirectAttributes,message);
        return "redirect:/members/login";
    }

    //회원가입 폼 요청
    @GetMapping("/add")
    public String addMemberForm(@ModelAttribute("member") MemberRequest member) {
        return "page/member/addMemberForm";
    }

    //회원 가입
    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute("member") MemberRequest member, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "page/member/addMemberForm";
        }

        memberService.saveMember(member);

        String message = "회원 가입이 완료되었습니다. 로그인 해 주세요";
        redirectMessage(redirectAttributes,message);
        return "redirect:/members/login";
    }

    //회원 수 카운팅(ID 중복체크)
    @GetMapping("/member-count")
    @ResponseBody
    public Integer countMemberByLoginId(@RequestParam final String loginId) {
        return memberService.countMemberByLoginId(loginId);
    }

    /**
     * 미사용
     */
    //회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    /**
     * 미사용
     */
    //회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
        return memberService.updateMember(params);
    }

    /**
     * 미사용
     */
    //회원 정보 삭제(회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteMemberById(final Long id) {
        return memberService.deleteMemberById(id);
    }

    private static void redirectMessage(RedirectAttributes redirectAttributes, String Message) {
        redirectAttributes.addFlashAttribute("message", Message);
        redirectAttributes.addFlashAttribute("status",true);
    }

}
