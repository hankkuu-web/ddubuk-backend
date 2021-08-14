package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.goods.Goods;
import gdg.six.ddoview.core.domain.goods.GoodsImageRepository;
import gdg.six.ddoview.core.domain.goods.GoodsRepository;
import gdg.six.ddoview.core.domain.goods.dto.GoodsTitleResponse;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.MemberRepository;
import gdg.six.ddoview.core.domain.order.OrderRepository;
import gdg.six.ddoview.core.domain.order.Orders;
import gdg.six.ddoview.core.domain.order.Ticketing;
import gdg.six.ddoview.core.domain.order.TicketingRepository;
import gdg.six.ddoview.core.domain.order.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final GoodsRepository goodsRepository;
    private final MemberService memberService;

    private final TicketingRepository ticketingRepository;

    public OrderMemberResponse getOrderWithMember(long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        Goods goods = order.getGoods();

        return OrderMemberResponse.builder()
                .goods(goods)
                .order(order)
                .build();
    }

    public OrderResponse get(long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        return OrderResponse.builder()
                .goods(order.getGoods())
                .orders(order)
                .build();
    }

    public List<OrderTitleResponse> getOrderAll() {
        List<Orders> orders = orderRepository.findAll();
        List<Goods> goods = goodsRepository.findAll();

        List<OrderTitleResponse> tt = orders.stream()
                .filter(o -> o.getStartDate().isAfter(LocalDateTime.now()))
                .map(o -> {

                    Goods g = goods.stream()
                            .filter(g1 -> g1.getId() == o.getGoods().getId())
                            .findAny()
                            .orElseThrow();

                    return OrderTitleResponse.builder()
                            .goods(g)
                            .order(o)
                            .build();

                })
                .collect(Collectors.toList());

        return tt;
    }

    public void reservations(ReservationRequest request) {
        Member member = memberService.get(request.getMemberId());
        Orders order = orderRepository.findById(request.getOrderId()).orElseThrow();

        Ticketing ticketing = Ticketing.builder()
                .member(member)
                .order(order).build();

        order.addTicketing(ticketing);

        ticketingRepository.save(ticketing);
    }

//    public List<Review> getAll() {
//
//        return  reviewRepository.findAll();
//    }

//    public List<Order> getByCompanyId(long companyId) {
//        return orderRepository.findAllByCompanyId(companyId);
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Orders create(OrderSetRequest request) {
        Goods goods = goodsRepository.findById(request.getGoodsId())
                .orElseThrow();

        Orders order = Orders.builder()
                .goods(goods)
                .paymentType(request.getPaymentType())
                .orderPrice(request.getOrderPrice())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        return orderRepository.save(order);
    }


//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public OrderSetRequest.Response set(OrderSetRequest request) {
//        Order order = orderRepository.findByCompanyIdAndMemberIdAndTitle(request.getCompanyId(), request.getMemberId(), request.getReviewTitle())
//                .orElseThrow();
//
//        Goods goods = goodsRepository.findById(request.getCompanyId())
//                .orElseThrow();
//
//        List<ReviewQuestion> list = request.getReviewQuestRequests()
//                .stream()
//                .map(x -> {
//                            GoodsReply question = goodsImageRepository.getById(x.getQuestionId());
//                            return ReviewQuestion.builder()
//                                    .answer(x.getAnswer())
//                                    .company(goods)
//                                    .review(order)
//                                    .question(question)
//                                    .build();
//                        }
//                ).collect(Collectors.toList());
//
//        order.updateReviewQuestion(list);
//        Order r = orderRepository.save(order);
//        reviewQuestRepository.saveAll(list);
//
//        return OrderSetRequest.Response.builder()
//                .reviewId(r.getId())
//                .build();
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void setReply(ReplyAdminSetRequest request) {
//        Order order = orderRepository.findById(request.getReviewId())
//                .orElseThrow();
//
//        Ticketing ticketing = order.findReply(request.getReplyId());
//        ticketing.updateContent(request.getContent());
//
//        orderRepository.save(order);
//    }

}
