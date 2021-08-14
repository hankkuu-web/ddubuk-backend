package gdg.six.ddoview.core.domain.goods.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GoodsTitleResponse {

    private long goodsId;

    private String goodsName;

    private long price;

    private String thumbnailImage;

    private String nationalName;

    private String cityName;

    @Builder
    public GoodsTitleResponse(Goods goods) {
        this.goodsId = goods.getId();
        this.goodsName = goods.getGoodsName();
        this.price = goods.getPrice();
        this.thumbnailImage = goods.getThumbnailImage();
        this.nationalName = goods.getNationalName();
        this.cityName = goods.getCityName();
    }
}
