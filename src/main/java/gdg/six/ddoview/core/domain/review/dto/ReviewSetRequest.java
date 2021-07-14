package gdg.six.ddoview.core.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ReviewSetRequest {

    @NotBlank
    private String reviewTitle;
    @NotNull
    private long companyId;
    @NotNull
    private long memberId;

    private List<ReviewQuestionRequest> reviewQuestRequests;

    @Getter
    public static class ReviewQuestionRequest {
        private long questionId;
        private String answer;
    }

    @Getter
    public static class Response {
        private long reviewId;
        @Builder
        public Response(long reviewId) {
            this.reviewId = reviewId;
        }
    }
}
