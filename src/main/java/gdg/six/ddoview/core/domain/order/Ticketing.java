package gdg.six.ddoview.core.domain.order;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Ticketing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Orders order;

    private LocalDateTime reservationDate;

    private LocalDateTime cancelDate;

    @Builder
    public Ticketing(Orders order, Member member) {
        this.order = order;
        this.member = member;
        this.reservationDate = LocalDateTime.now();
        this.cancelDate = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0);
    }


//

//
//    public void updateContent(String content) {
//        this.content = content;
//    }
}
