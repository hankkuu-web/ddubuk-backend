package gdg.six.ddoview.core.domain.review.dto;

import gdg.six.ddoview.core.domain.review.Reply;
import gdg.six.ddoview.core.domain.review.Review;
import gdg.six.ddoview.core.domain.review.ReviewQuestion;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReviewGetResponse {

    private long reviewId;

    private long companyId;

    private long memberId;

    private String title;

    private List<ReviewQuestionResponse> ReviewQuestions;

    private List<ReplyResponse> replies;

    @Getter
    public static class ReviewQuestionResponse {
        private long questionId;
        private String question;
        private String answer;
        @Builder
        public ReviewQuestionResponse(ReviewQuestion reviewQuestion) {
            this.answer = reviewQuestion.getAnswer();
            this.questionId = reviewQuestion.getId();
            this.question = reviewQuestion.getQuestion().getContent();
        }
    }

    @Getter
    public static class ReplyResponse {

        private long replyId;

        private String content;

        @Builder
        public ReplyResponse(Reply reply) {
            this.replyId = reply.getId();
            this.content = reply.getContent();
        }

    }

    @Builder
    public ReviewGetResponse(Review review) {
        this.reviewId = review.getId();
        this.companyId = review.getCompany().getId();
        this.memberId = review.getMember().getId();
        this.title = review.getTitle();
        this.ReviewQuestions = review.getReviewQuestions()
                .stream()
                .map(r -> ReviewQuestionResponse.builder()
                        .reviewQuestion(r)
                        .build()
                ).collect(Collectors.toList());
        this.replies = review.getReplies()
                .stream()
                .map(r -> ReplyResponse.builder()
                        .reply(r)
                        .build()
                ).collect(Collectors.toList());
    }
}
