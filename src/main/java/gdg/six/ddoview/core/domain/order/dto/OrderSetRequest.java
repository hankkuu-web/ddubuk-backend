package gdg.six.ddoview.core.domain.order.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OrderSetRequest {

    @NotNull
    private long goodsId;

    private String paymentType;

    private long orderPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public OrderSetRequest(long goodsId, String paymentType, long orderPrice, LocalDateTime startDate, LocalDateTime endDate) {
        this.goodsId = goodsId;
        this.paymentType = paymentType;
        this.orderPrice = orderPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    private List<ReviewQuestionRequest> reviewQuestRequests;
//
//    @Getter
//    public static class ReviewQuestionRequest {
//        private long questionId;
//        private String answer;
//    }
//
//    @Getter
//    public static class Response {
//        private long reviewId;
//        @Builder
//        public Response(long reviewId) {
//            this.reviewId = reviewId;
//        }
//    }
}
