package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.GoodsService;
import gdg.six.ddoview.core.application.MemberService;
import gdg.six.ddoview.core.application.OrderService;
import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.goods.dto.GoodsImageRequest;
import gdg.six.ddoview.core.domain.goods.dto.GoodsResponse;
import gdg.six.ddoview.core.domain.goods.dto.GoodsSetRequest;
import gdg.six.ddoview.core.domain.goods.dto.GoodsTitleResponse;
import gdg.six.ddoview.core.domain.member.dto.MemberRequest;
import gdg.six.ddoview.core.domain.order.dto.OrderSetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final GoodsService goodsService;
    private final MemberService memberService;
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/members")
    public MemberRequest.CreateMemberResponse createMember(@Valid @RequestBody MemberRequest.CreateMember request) {
        return memberService.create(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/goods")
    public Goods createGoods(@Valid @RequestBody GoodsSetRequest request) {
        return goodsService.create(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/orders")
    public void createOrder(@Valid @RequestBody OrderSetRequest request) {
        orderService.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/goodsImages")
    public void updateGoodsImage(@Valid @RequestBody GoodsImageRequest request) {
        goodsService.setImages(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{goodsId}")
    public GoodsResponse getGoods(@PathVariable long goodsId) {
        return goodsService.get(goodsId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/goods")
    public List<GoodsTitleResponse> getGoods() {
        return goodsService.getFindAll();
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PatchMapping("/review")
//    public OrderSetRequest.Response updateReview(@Valid @RequestBody OrderSetRequest request) {
//        return orderService.set(request);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PatchMapping("/reply")
//    public void updateReply(@Valid @RequestBody ReplyAdminSetRequest request) {
//        orderService.setReply(request);
//    }
//

}
