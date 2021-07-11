package gdg.six.ddoview.core.domain.company.dto;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class CompanyResponse {
    private long companyId;

    private String name;

    private String profilePhotoUrl;

    private String introduce;

    private String description;

    private String serviceDescription;

    //private Set<Question> questions;

    @Builder
    public CompanyResponse(Company company) {
        this.companyId = company.getId();
        this.name = company.getName();
        this.profilePhotoUrl = company.getProfilePhotoUrl();
        this.introduce = company.getIntroduce();
        this.description = company.getDescription();
        this.serviceDescription = company.getServiceDescription();
        //this.questions = company.getQuestions();
    }
}
