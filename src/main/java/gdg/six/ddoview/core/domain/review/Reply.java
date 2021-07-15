package gdg.six.ddoview.core.domain.review;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentReplyId")
    private Reply parentReply;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentReply", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Reply> childReply = new HashSet<>();

    private String content;

    @Builder
    public Reply(Review review, String content, Member member) {
        this.review = review;
        this.content = content;
        this.member = member;
    }

    public Reply addChildReply(String content, Member member) {
        Reply childReply = Reply.builder()
                .review(this.review)
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

    public void updateContent(String content) {
        this.content = content;
    }
}
