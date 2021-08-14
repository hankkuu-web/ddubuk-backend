package gdg.six.ddoview.core.domain.order.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OrderTitleResponse {

    private long goodsId;

    private long orderId;

    private long orderPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String nationalName;

    private String cityName;

    private String goodsName;

    @Builder
    public OrderTitleResponse(Goods goods, Orders order) {
        this.goodsId = goods.getId();
        this.orderId = order.getId();
        this.orderPrice = order.getOrderPrice();
        this.startDate = order.getStartDate();
        this.endDate = order.getEndDate();
        this.nationalName = goods.getNationalName();
        this.cityName = goods.getCityName();
        this.goodsName = goods.getGoodsName();
    }
}
