package hello.board;

import hello.board.domain.member.MemberResponse;
import hello.board.domain.member.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "page/main/main";
    }
}
