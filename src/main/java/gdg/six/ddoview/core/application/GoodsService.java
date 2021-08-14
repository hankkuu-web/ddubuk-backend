package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.goods.*;
import gdg.six.ddoview.core.domain.goods.dto.GoodsImageRequest;
import gdg.six.ddoview.core.domain.goods.dto.GoodsResponse;
import gdg.six.ddoview.core.domain.goods.dto.GoodsSetRequest;
import gdg.six.ddoview.core.domain.goods.dto.GoodsTitleResponse;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.dto.ReplySetRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final MemberService memberService;
    private final GoodsImageRepository goodsImageRepository;

    public GoodsResponse get(long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow();

        return GoodsResponse.builder()
                .goods(goods)
                .build();
    }

    public List<GoodsTitleResponse> getFindAll() {
        List<Goods> list = goodsRepository.findAll();

        return list.stream()
                .map(g -> GoodsTitleResponse.builder()
                        .goods(g)
                        .build())
                .collect(Collectors.toList());
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void createReply(ReplySetRequest request) {
        Member member = memberService.get(request.getMemberId());

        Goods goods = goodsRepository.findById(request.getGoodsId())
                .orElseThrow();

        if(request.checkChildReply()) {
            GoodsReply parentReply = goods.findReply(request.getParentReplyId());
            GoodsReply childReply = parentReply.addChildReply(request.getContent(), member);
            goods.addReply(childReply);
        } else {
            goods.addReply(GoodsReply.builder()
                    .content(request.getContent())
                    .goods(goods)
                    .member(member)
                    .build());
        }

        goodsRepository.save(goods);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setImages(GoodsImageRequest request) {

        Goods goods = goodsRepository.findById(request.getGoodsId())
                .orElseThrow();

        List<GoodsImage> images = request.getImageUrl()
                .stream()
                .map(image ->
                        GoodsImage.builder()
                                .goods(goods)
                                .url(image)
                                .build())
                .collect(Collectors.toList());

        goods.addImages(images);
        goodsImageRepository.saveAll(images);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Goods create(GoodsSetRequest request) {
        Goods goods = request.toGoods();
        return goodsRepository.save(goods);
    }
//
//    public CompanyReviewResponse getCompanyWithReview(long companyId) {
//        List<Order> orders = reviewService.getByCompanyId(companyId);
//        Goods goods = goodsRepository.findById(companyId).orElseThrow();
//
//        return CompanyReviewResponse.builder()
//                .company(goods)
//                .reviews(orders)
//                .build();
//    }
}
