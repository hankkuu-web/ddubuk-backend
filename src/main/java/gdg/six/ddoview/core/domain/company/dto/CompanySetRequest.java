package gdg.six.ddoview.core.domain.company.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CompanySetRequest {

    @NotNull
    private Long companyId;

    @NotBlank
    private String name;

    @NotBlank
    private String profilePhotoUrl;

    @NotBlank
    private String companyCategory;

    @NotBlank
    private String introduce;

    @NotBlank
    private String description;

    @NotBlank
    private String serviceDescription;

    private List<QuestionRequest> questions;

    @Getter
    public static class QuestionRequest {
        private String content;
    }
}
