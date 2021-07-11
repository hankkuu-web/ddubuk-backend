package gdg.six.ddoview.core.domain.company;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.review.ReviewQuestion;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

//    @OneToMany(mappedBy = "question")
//    private Set<ReviewQuestion> reviewQuestions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;

    @Builder
    public Question(String content, Company company) {
        this.content = content;
        this.company = company;
    }
}
