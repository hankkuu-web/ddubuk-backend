package gdg.six.ddoview.core.domain.goods.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.goods.GoodsImage;
import gdg.six.ddoview.core.domain.goods.GoodsReply;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GoodsResponse {

    private long goodsId;

    private String description;

    private long price;

    private String nationalName;

    private String cityName;

    private String goodsName;

    private List<GoodsImageResponse> images;

    private List<GoodsReplyResponse> replies;

    @Builder
    public GoodsResponse(Goods goods) {
        this.goodsId = goods.getId();
        this.description = goods.getDescription();
        this.price = goods.getPrice();
        this.nationalName = goods.getNationalName();
        this.cityName = goods.getCityName();
        this.goodsName = goods.getGoodsName();

        this.images = goods.getGoodsImages().stream()
                .map(image -> GoodsImageResponse.builder()
                        .image(image).build())
                .collect(Collectors.toList());

        this.replies = goods.getGoodsReplies()
                .stream()
                .filter(reply -> reply.isParentReply())
                .map(reply -> GoodsReplyResponse.builder()
                        .reply(reply).build())
                .collect(Collectors.toList());


    }
}
