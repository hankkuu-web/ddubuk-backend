package gdg.six.ddoview.core.domain.company;

import gdg.six.ddoview.common.BaseEntity;
import gdg.six.ddoview.core.domain.company.dto.CompanySetRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String companyCategory;

    private String profilePhotoUrl;

    private String introduce;

    private String description;

    private String serviceDescription;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Company(String name, String profilePhotoUrl) {
        this.name = name;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public void updateTemplate(CompanySetRequest request) {
        this.companyCategory = request.getCompanyCategory();
        this.description = request.getDescription();
        this.introduce = request.getIntroduce();
        this.serviceDescription = request.getServiceDescription();

        if(request.getQuestions().size() > 0) {
            this.questions.forEach(q -> q.setActive(false));

            for(CompanySetRequest.QuestionRequest question : request.getQuestions()) {
                Question q = Question.builder()
                        .content(question.getContent())
                        .company(this)
                        .isActive(true)
                        .build();
                this.addQuestion(q);
            }
        }
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
