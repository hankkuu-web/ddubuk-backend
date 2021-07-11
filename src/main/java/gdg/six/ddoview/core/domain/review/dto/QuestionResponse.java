package gdg.six.ddoview.core.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionResponse {
    private long questionId;

    private String questionContent;

    @Builder
    public QuestionResponse(long questionId, String questionContent) {
        this.questionId = questionId;
        this.questionContent = questionContent;
    }
}
