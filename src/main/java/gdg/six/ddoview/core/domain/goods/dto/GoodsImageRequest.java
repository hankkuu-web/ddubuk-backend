package gdg.six.ddoview.core.domain.goods.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GoodsImageRequest {

    private Long goodsId;

    private List<String> imageUrl;
}
