package gdg.six.ddoview.core.domain.order.dto;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OrderResponse {

    private long orderId;

    private long goodsId;

    private String description;

    private String nationalName;

    private String cityName;

    private String goodsName;

    private String paymentType;

    private long orderPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public OrderResponse(Orders orders, Goods goods) {
        this.orderId = orders.getId();
        this.goodsId = goods.getId();
        this.description = goods.getDescription();
        this.orderPrice = orders.getOrderPrice();
        this.nationalName = goods.getNationalName();
        this.cityName = goods.getCityName();
        this.goodsName = goods.getGoodsName();
        this.paymentType = orders.getPaymentType();
        this.startDate = orders.getStartDate();
        this.endDate = orders.getEndDate();
    }
}
