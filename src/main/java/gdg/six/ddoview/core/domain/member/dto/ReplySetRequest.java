package gdg.six.ddoview.core.domain.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
public class ReplySetRequest {
    @NotNull
    private Long goodsId;

    @NotNull
    private Long memberId;

    private Long parentReplyId;

    @NotBlank
    private String content;

    public boolean checkChildReply() {
        return !Objects.isNull(parentReplyId);
    }
}
