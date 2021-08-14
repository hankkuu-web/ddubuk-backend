package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.GoodsService;
import gdg.six.ddoview.core.application.OrderService;
import gdg.six.ddoview.core.domain.goods.dto.GoodsResponse;
import gdg.six.ddoview.core.domain.goods.dto.GoodsTitleResponse;
import gdg.six.ddoview.core.domain.member.dto.ReplySetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/goods")
@RestController
public class GoodsController {

    private final GoodsService goodsService;
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/replies")
    public void createReply(@Valid @RequestBody ReplySetRequest request) {
        goodsService.createReply(request);
    }



//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{companyId}/reviews")
//    public CompanyReviewResponse getCompanyWithReview(@PathVariable long companyId) {
//         return goodsService.getCompanyWithReview(companyId);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PatchMapping("")
//    public void setCompany(@Valid @RequestBody CompanySetRequest request) {
//        goodsService.set(request);
//    }
//

}
