package gdg.six.ddoview.core.domain.review;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "REVIEW_QUESTION")
public class ReviewQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;

    private String answer;

    @Builder
    public ReviewQuestion(Review review, Question question, Company company, String answer) {
        this.review = review;
        this.company = company;
        this.answer = answer;
        this.question = question;
    }
}
