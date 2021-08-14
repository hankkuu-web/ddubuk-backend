package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.goods.GoodsReply;
import gdg.six.ddoview.core.domain.goods.GoodsRepository;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.MemberRepository;
import gdg.six.ddoview.core.domain.member.dto.MemberRequest;
import gdg.six.ddoview.core.domain.member.dto.ReplySetRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member get(long memberId) {

        return memberRepository.findById(memberId).orElseThrow();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public MemberRequest.CreateMemberResponse create(MemberRequest.CreateMember request) {
        Member member = request.toMember();
        Member m = memberRepository.save(member);

        return MemberRequest.CreateMemberResponse.builder()
                .memberId(m.getId())
                .build();
    }
}
