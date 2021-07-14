package gdg.six.ddoview.core.domain.review.dto;

import gdg.six.ddoview.core.domain.review.Reply;
import gdg.six.ddoview.core.domain.review.Review;
import gdg.six.ddoview.core.domain.review.ReviewQuestion;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        private String memberName;

        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        private List<ChildReplyResponse> childReplyResponses = new ArrayList<>();

        @Builder
        public ReplyResponse(Reply reply) {
            this.memberName = reply.getMember().getName();
            this.replyId = reply.getId();
            this.content = reply.getContent();
            this.createDate = reply.getCreatedAt();
            this.updateDate = reply.getUpdatedAt();

            this.childReplyResponses = reply.getChildReply()
                    .stream()
                    .map(r -> ChildReplyResponse.builder()
                            .reply(r).build())
                            .sorted()
                    .collect(Collectors.toList());
        }

    }

    @Getter
    public static class ChildReplyResponse {
        private long replyId;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String memberName;

        @Builder
        public ChildReplyResponse(Reply reply) {
            this.memberName = reply.getMember().getName();
            this.replyId = reply.getId();
            this.content = reply.getContent();
            this.createDate = reply.getCreatedAt();
            this.updateDate = reply.getUpdatedAt();
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
                //.sorted(a)
                .map(r -> ReviewQuestionResponse.builder()
                        .reviewQuestion(r)
                        .build()
                ).collect(Collectors.toList());
        this.replies = review.getReplies()
                .stream()
                .filter(r -> r.isParentReply())
                .map(r -> ReplyResponse.builder()
                        .reply(r)
                        .build()
                ).collect(Collectors.toList());
    }
}
