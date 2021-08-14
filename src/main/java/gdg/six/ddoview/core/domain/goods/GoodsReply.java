package gdg.six.ddoview.core.domain.goods;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class GoodsReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsId")
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentReplyId")
    private GoodsReply parentReply;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentReply", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GoodsReply> childReply = new ArrayList<>();

    @Builder
    public GoodsReply(String content, Goods goods, Member member) {
        this.content = content;
        this.goods = goods;
        this.member = member;
    }

    public GoodsReply addChildReply(String content, Member member) {
        GoodsReply childReply = GoodsReply.builder()
                .goods(this.goods)
                .content(content)
                .member(member)
                .build();

        this.childReply.add(childReply);
        childReply.parentReply = this;
        return childReply;
    }

    public boolean isParentReply() {
        return Objects.isNull(parentReply);
    }

}
