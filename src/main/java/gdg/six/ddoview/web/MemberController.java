package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.MemberService;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable long memberId) {
        return memberService.get(memberId);
    }

}
