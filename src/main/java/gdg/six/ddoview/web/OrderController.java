package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.OrderService;
import gdg.six.ddoview.core.domain.order.dto.OrderMemberResponse;
import gdg.six.ddoview.core.domain.order.dto.OrderResponse;
import gdg.six.ddoview.core.domain.order.dto.OrderTitleResponse;
import gdg.six.ddoview.core.domain.order.dto.ReservationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/orders/{orderId}/members")
    public OrderMemberResponse getOrderWithMember(@PathVariable long orderId) {
        return orderService.getOrderWithMember(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goods/{goodsId}")
    public OrderResponse getOrder(@PathVariable long goodsId) {
        return orderService.get(goodsId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goods")
    public List<OrderTitleResponse> getOrderAll() {
        return orderService.getOrderAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders/reservations")
    public void reservations(@Valid @RequestBody ReservationRequest request) {
        orderService.reservations(request);
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PatchMapping("/orders")
//    public void createReply(@Valid @RequestBody ReplySetRequest request) {
//        reviewService.createReply(request);
//    }

}
