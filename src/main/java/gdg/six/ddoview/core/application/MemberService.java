package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.CompanyRepository;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.MemberRepository;
import gdg.six.ddoview.core.domain.member.MemberType;
import gdg.six.ddoview.core.domain.member.dto.MemberRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    public Member get(long memberId) {

        return memberRepository.findById(memberId).orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public MemberRequest.CreateMemberResponse register(MemberRequest.CreateMember request) {
        Member member = request.toMember();

        if(request.getMemberType() == MemberType.Company) {
            Company company = request.toCompany();
            company = companyRepository.save(company);
            member.updateCompanyId(company.getId());
        }
        Member m = memberRepository.save(member);

        return MemberRequest.CreateMemberResponse.builder()
                .memberId(m.getId())
                .build();
    }
}
