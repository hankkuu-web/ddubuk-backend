package gdg.six.ddoview.core.domain.order.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ReservationRequest {
    @NotNull
    private Long orderId;

    @NotNull
    private Long goodsId;

    @NotNull
    private Long memberId;
}
