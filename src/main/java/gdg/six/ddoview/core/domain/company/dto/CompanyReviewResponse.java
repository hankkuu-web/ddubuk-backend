package gdg.six.ddoview.core.domain.company.dto;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.review.Review;
import gdg.six.ddoview.core.domain.review.dto.QuestionResponse;
import gdg.six.ddoview.core.domain.review.dto.ReviewResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CompanyReviewResponse {
    private long id;

    private String name;

    private String profilePhotoUrl;

    private String introduce;

    private String description;

    private String serviceDescription;

    private List<ReviewResponse> reviews;

    private List<QuestionResponse> questions;

    @Builder
    public CompanyReviewResponse(Company company, List<Review> reviews) {
        this.id = company.getId();
        this.name = company.getName();
        this.profilePhotoUrl = company.getProfilePhotoUrl();
        this.description = company.getDescription();
        this.introduce = company.getIntroduce();
        this.serviceDescription = company.getServiceDescription();

        this.reviews = reviews.stream()
                .map(r -> ReviewResponse.builder()
                        .review(r)
                        .build())
                .collect(Collectors.toList());

        this.questions = company.getQuestions()
                .stream()
                .map(q -> QuestionResponse.builder()
                        .questionId(q.getId())
                        .questionContent(q.getContent())
                        .build()
                ).collect(Collectors.toList());

    }
}
