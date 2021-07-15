package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.CompanyRepository;
import gdg.six.ddoview.core.domain.company.Question;
import gdg.six.ddoview.core.domain.company.QuestionRepository;
import gdg.six.ddoview.core.domain.member.Member;
import gdg.six.ddoview.core.domain.member.MemberRepository;
import gdg.six.ddoview.core.domain.review.*;
import gdg.six.ddoview.core.domain.review.dto.ReplyAdminSetRequest;
import gdg.six.ddoview.core.domain.review.dto.ReplySetRequest;
import gdg.six.ddoview.core.domain.review.dto.ReviewGetResponse;
import gdg.six.ddoview.core.domain.review.dto.ReviewSetRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final ReviewQuestRepository reviewQuestRepository;

    public ReviewGetResponse getReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        return ReviewGetResponse.builder()
                .review(review)
                .build();
    }

//    public List<Review> getAll() {
//
//        return  reviewRepository.findAll();
//    }

    public List<Review> getByCompanyId(long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ReviewSetRequest.Response createReview(ReviewSetRequest request) {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow();

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow();

        Review review = Review.builder()
                .company(company)
                .member(member)
                .title(request.getReviewTitle())
                .build();

        List<ReviewQuestion> list = request.getReviewQuestRequests()
                .stream()
                .map(x -> {
                    Question question = questionRepository.getById(x.getQuestionId());
                    return ReviewQuestion.builder()
                                    .answer(x.getAnswer())
                                    .company(company)
                                    .review(review)
                                    .question(question)
                                    .build();
                        }
                ).collect(Collectors.toList());


        review.updateReviewQuestion(list);
        Review r = reviewRepository.save(review);
        reviewQuestRepository.saveAll(list);

        return ReviewSetRequest.Response.builder()
                .reviewId(r.getId())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createReply(ReplySetRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow();

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow();

        if(request.checkChildReply()) {
            Reply parentReply = review.findReply(request.getParentReplyId());
            Reply childReply = parentReply.addChildReply(request.getContent(), member);
            review.addReply(childReply);
        } else {
            review.addReply(Reply.builder()
                    .content(request.getContent())
                    .review(review)
                    .member(member)
                    .build());
        }

        reviewRepository.save(review);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ReviewSetRequest.Response set(ReviewSetRequest request) {
        Review review = reviewRepository.findByCompanyIdAndMemberIdAndTitle(request.getCompanyId(), request.getMemberId(), request.getReviewTitle())
                .orElseThrow();

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow();

        List<ReviewQuestion> list = request.getReviewQuestRequests()
                .stream()
                .map(x -> {
                            Question question = questionRepository.getById(x.getQuestionId());
                            return ReviewQuestion.builder()
                                    .answer(x.getAnswer())
                                    .company(company)
                                    .review(review)
                                    .question(question)
                                    .build();
                        }
                ).collect(Collectors.toList());

        review.updateReviewQuestion(list);
        Review r = reviewRepository.save(review);
        reviewQuestRepository.saveAll(list);

        return ReviewSetRequest.Response.builder()
                .reviewId(r.getId())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setReply(ReplyAdminSetRequest request) {
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow();

        Reply reply = review.findReply(request.getReplyId());
        reply.updateContent(request.getContent());

        reviewRepository.save(review);
    }

}
