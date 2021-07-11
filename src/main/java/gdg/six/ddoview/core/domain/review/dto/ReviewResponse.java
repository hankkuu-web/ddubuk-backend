package gdg.six.ddoview.core.domain.review.dto;

import gdg.six.ddoview.core.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewResponse {

    private long reviewId;

    private String reviewTitle;

    private String memberName;

    private LocalDateTime createDate;

    private List<QuestionResponse> questions;

    @Builder
    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.reviewTitle = review.getTitle();
        this.memberName = review.getMember().getName();
        this.createDate = review.getCreatedAt();

        this.questions = review.getReviewQuestions()
                .stream()
                .map(reviewQuestion -> QuestionResponse.builder()
                        .questionId(reviewQuestion.getQuestion().getId())
                        .questionContent(reviewQuestion.getQuestion().getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
