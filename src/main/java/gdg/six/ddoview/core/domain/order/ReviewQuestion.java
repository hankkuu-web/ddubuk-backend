//package gdg.six.ddoview.core.domain.order;
//
//import gdg.six.ddoview.common.BaseEntity;
//import gdg.six.ddoview.core.domain.goods.Goods;
//import gdg.six.ddoview.core.domain.goods.GoodsReply;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@Entity
//@Table(name = "REVIEW_QUESTION")
//public class ReviewQuestion extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reviewId")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "questionId")
//    private GoodsReply question;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "companyId")
//    private Goods goods;
//
//    private String answer;
//
//    @Builder
//    public ReviewQuestion(Order order, GoodsReply question, Goods goods, String answer) {
//        this.order = order;
//        this.goods = goods;
//        this.answer = answer;
//        this.question = question;
//    }
//}
