package gdg.six.ddoview.core.domain.goods;

import gdg.six.ddoview.common.BaseEntity;
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
public class Goods extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private long price;

    private String nationalName;

    private String cityName;

    private String goodsName;

    private String thumbnailImage;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoodsImage> goodsImages = new ArrayList<>();

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoodsReply> goodsReplies = new ArrayList<>();

    @Builder
    public Goods(String description, long price, String nationalName
                 , String cityName, String goodsName, String thumbnailImage) {
        this.description = description;
        this.price = price;
        this.nationalName = nationalName;
        this.cityName = cityName;
        this.goodsName = goodsName;
        this.thumbnailImage = thumbnailImage;
    }
//
//    public void updateTemplate(CompanySetRequest request) {
//        this.companyCategory = request.getCompanyCategory();
//        this.description = request.getDescription();
//        this.introduce = request.getIntroduce();
//        this.serviceDescription = request.getServiceDescription();
//
//        if(request.getQuestions().size() > 0) {
//            this.questions.forEach(q -> q.setActive(false));
//
//            for(CompanySetRequest.QuestionRequest question : request.getQuestions()) {
//                GoodsReply q = GoodsReply.builder()
//                        .content(question.getContent())
//                        .company(this)
//                        .isActive(true)
//                        .build();
//                this.addQuestion(q);
//            }
//        }
//    }
//
    public void addImages(List<GoodsImage> image) {
        this.goodsImages.addAll(image);
    }

    public GoodsReply findReply(long replyId) {
        return this.goodsReplies.stream()
                .filter(r -> r.getId() == replyId)
                .findFirst()
                .orElseThrow();
    }

    public void addReply(GoodsReply reply) {
        this.goodsReplies.add(reply);
    }

}
