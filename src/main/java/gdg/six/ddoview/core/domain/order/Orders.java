package gdg.six.ddoview.core.domain.order;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.goods.Goods;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsId")
    private Goods goods;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticketing> ticketing = new ArrayList<>();

    private String paymentType;

    private long orderPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public Orders(Goods goods, String paymentType, long orderPrice, LocalDateTime startDate, LocalDateTime endDate) {
        this.paymentType = paymentType;
        this.orderPrice = orderPrice;
        this.goods = goods;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addTicketing(Ticketing ticketing) {
        this.ticketing.add(ticketing);
    }

//
//    public void updateReviewQuestion(List<ReviewQuestion> reviewQuestionList) {
//        this.reviewQuestions.clear();
//        this.reviewQuestions = new HashSet<>(reviewQuestionList);
//    }
//

}
