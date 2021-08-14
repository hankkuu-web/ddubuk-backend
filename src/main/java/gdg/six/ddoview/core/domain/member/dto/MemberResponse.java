package gdg.six.ddoview.core.domain.member.dto;

import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.order.Ticketing;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private Long memberId;

    private String memberName;

    private LocalDateTime reservationDate;

    private LocalDateTime cancelDate;

    @Builder
    public MemberResponse(Member member, Ticketing ticketing) {
        this.memberId = member.getId();
        this.memberName = member.getName();
        this.reservationDate = ticketing.getReservationDate();
        this.cancelDate = ticketing.getCancelDate();
    }
}
