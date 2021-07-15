package gdg.six.ddoview.core.domain.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReplyAdminSetRequest {
    @NotNull
    private Long reviewId;

    @NotNull
    private Long replyId;

    @NotBlank
    private String content;
}
