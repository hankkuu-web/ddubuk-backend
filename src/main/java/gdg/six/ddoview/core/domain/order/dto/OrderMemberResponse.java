package gdg.six.ddoview.core.domain.order.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.member.dto.MemberResponse;
import gdg.six.ddoview.core.domain.order.Orders;
import gdg.six.ddoview.core.domain.order.Ticketing;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class OrderMemberResponse {

    private long goodsId;

    private String goodsName;

    private String paymentType;

    private long orderPrice;

    private LocalDateTime createDate;

    private List<MemberResponse> members;

    @Builder
    public OrderMemberResponse(Orders order, Goods goods) {
        this.goodsId = goods.getId();
        this.goodsName = goods.getGoodsName();
        this.paymentType = order.getPaymentType();
        this.orderPrice = order.getOrderPrice();
        this.createDate = order.getCreatedAt();

        this.members = order.getTicketing().stream()
                .filter(t -> t.getCancelDate().toLocalDate().isEqual(LocalDate.of(1900, Month.JANUARY, 1)) == true)
                .map(t -> MemberResponse.builder()
                        .member(t.getMember())
                        .ticketing(t).build()
                ).collect(Collectors.toList());

//        this.questions = order.getReviewQuestions()
//                .stream()
//                .map(reviewQuestion -> QuestionResponse.builder()
//                        .questionId(reviewQuestion.getQuestion().getId())
//                        .questionContent(reviewQuestion.getQuestion().getContent())
//                        .build())
//                .collect(Collectors.toList());
    }
}
