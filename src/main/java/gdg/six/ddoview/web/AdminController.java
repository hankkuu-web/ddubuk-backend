package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.CompanyService;
import gdg.six.ddoview.core.application.MemberService;
import gdg.six.ddoview.core.domain.company.dto.CompanySetRequest;
import gdg.six.ddoview.core.domain.member.dto.MemberRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final CompanyService companyService;
    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public MemberRequest.CreateMemberResponse register(@Valid @RequestBody MemberRequest.CreateMember request) {
        return memberService.register(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/company")
    public void createCompany(@Valid @RequestBody CompanySetRequest request) {
        companyService.create(request);
    }

}
