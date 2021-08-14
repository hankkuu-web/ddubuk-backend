package gdg.six.ddoview.core.domain.goods.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class GoodsSetRequest {

    private Long goodsId;

    @NotBlank
    private String goodsName;

    @NotBlank
    private String goodsThumbnail;

    @NotBlank
    private String goodsDescription;

    @NotNull
    private long goodsPrice;

    @NotBlank
    private String nationalName;

    @NotBlank
    private String cityName;


    public Goods toGoods() {
        return Goods.builder()
                .cityName(this.cityName)
                .description(this.goodsDescription)
                .goodsName(this.goodsName)
                .nationalName(this.nationalName)
                .price(this.goodsPrice)
                .thumbnailImage(this.goodsThumbnail)
                .build();
    }

}
