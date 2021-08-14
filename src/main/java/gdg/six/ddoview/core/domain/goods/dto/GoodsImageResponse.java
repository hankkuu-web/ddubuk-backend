package gdg.six.ddoview.core.domain.goods.dto;

import gdg.six.ddoview.core.domain.goods.GoodsImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GoodsImageResponse {

    private long id;

    private String url;

    @Builder
    public GoodsImageResponse(GoodsImage image) {
        this.id = image.getId();
        this.url = image.getImageUrl();
    }
}
