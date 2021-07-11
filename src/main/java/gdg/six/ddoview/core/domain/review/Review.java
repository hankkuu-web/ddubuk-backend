package gdg.six.ddoview.core.domain.review;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.Question;
import gdg.six.ddoview.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "review")
    private Set<ReviewQuestion> reviewQuestions = new HashSet<>();

    private String title;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Reply> replies = new HashSet<>();

    @Builder
    public Review(Company company, Member member, String title) {
        this.company = company;
        this.member = member;
        this.title = title;
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

    public void updateReviewQuestion(List<ReviewQuestion> reviewQuestionList) {
        this.reviewQuestions = new HashSet<>(reviewQuestionList);
    }
}
