package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.CompanyService;
import gdg.six.ddoview.core.application.MemberService;
import gdg.six.ddoview.core.application.ReviewService;
import gdg.six.ddoview.core.domain.company.dto.CompanySetRequest;
import gdg.six.ddoview.core.domain.member.dto.MemberRequest;
import gdg.six.ddoview.core.domain.review.dto.ReplyAdminSetRequest;
import gdg.six.ddoview.core.domain.review.dto.ReplySetRequest;
import gdg.six.ddoview.core.domain.review.dto.ReviewSetRequest;
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
    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/member")
    public MemberRequest.CreateMemberResponse register(@Valid @RequestBody MemberRequest.CreateMember request) {
        return memberService.register(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/company")
    public void createCompany(@Valid @RequestBody CompanySetRequest request) {
        companyService.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/company")
    public void updateCompany(@Valid @RequestBody CompanySetRequest request) {
        companyService.set(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/review")
    public ReviewSetRequest.Response updateReview(@Valid @RequestBody ReviewSetRequest request) {
        return reviewService.set(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/reply")
    public void updateReply(@Valid @RequestBody ReplyAdminSetRequest request) {
        reviewService.setReply(request);
    }


}
