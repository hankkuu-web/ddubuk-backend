package gdg.six.ddoview.core.domain.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReplySetRequest {
    @NotNull
    private long reviewId;
    @NotBlank
    private String content;
}
