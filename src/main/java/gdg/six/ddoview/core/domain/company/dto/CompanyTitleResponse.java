package gdg.six.ddoview.core.domain.company.dto;

import gdg.six.ddoview.core.domain.company.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyTitleResponse {

    private long companyId;

    private String companyName;

    private String companyCategory = "";

    private String introduce;

    @Builder
    public CompanyTitleResponse(Company company) {
        this.companyCategory = company.getCompanyCategory();
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.introduce = company.getIntroduce();
    }
}
