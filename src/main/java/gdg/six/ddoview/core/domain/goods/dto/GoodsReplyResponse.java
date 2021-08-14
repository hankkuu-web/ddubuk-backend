package gdg.six.ddoview.core.domain.goods.dto;

import gdg.six.ddoview.core.domain.goods.GoodsReply;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GoodsReplyResponse {

    private long replyId;

    private String content;

    private String memberName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<ChildReplyResponse> childReplyResponses = new ArrayList<>();

    @Builder
    public GoodsReplyResponse(GoodsReply reply) {
        this.memberName = reply.getMember().getName();
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.createDate = reply.getCreatedAt();
        this.updateDate = reply.getUpdatedAt();

        this.childReplyResponses = reply.getChildReply()
                .stream()
                .map(r -> ChildReplyResponse.builder()
                        .reply(r).build())
                .sorted((r1, r2) -> Long.compare(r1.getReplyId(), r2.getReplyId()))
                .collect(Collectors.toList());
    }


    @Getter
    public static class ChildReplyResponse {
        private long replyId;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String memberName;

        @Builder
        public ChildReplyResponse(GoodsReply reply) {
            this.memberName = reply.getMember().getName();
            this.replyId = reply.getId();
            this.content = reply.getContent();
            this.createDate = reply.getCreatedAt();
            this.updateDate = reply.getUpdatedAt();
        }
    }


}
