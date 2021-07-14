package gdg.six.ddoview.core.domain.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReplySetRequest {
    @NotNull
    private long reviewId;

    private long parentReplyId;

    @NotNull
    private Long memberId;

    @NotBlank
    private String content;

    public boolean addChildReply() {
        return parentReplyId != 0;
    }
}
