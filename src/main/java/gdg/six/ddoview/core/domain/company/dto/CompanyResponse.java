package gdg.six.ddoview.core.domain.company.dto;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CompanyResponse {
    private long companyId;

    private String name;

    private String companyCategory;

    private String profilePhotoUrl;

    private String introduce;

    private String description;

    private String serviceDescription;

    private List<QuestionResponse> questions;

    @Builder
    public CompanyResponse(Company company) {
        this.companyCategory = company.getCompanyCategory();
        this.companyId = company.getId();
        this.name = company.getName();
        this.profilePhotoUrl = company.getProfilePhotoUrl();
        this.introduce = company.getIntroduce();
        this.description = company.getDescription();
        this.serviceDescription = company.getServiceDescription();
        this.questions = company.getQuestions().stream()
                .filter(q -> q.isActive())
                .map(q -> QuestionResponse.builder()
                .question(q).build())
                .collect(Collectors.toList());
    }
}
