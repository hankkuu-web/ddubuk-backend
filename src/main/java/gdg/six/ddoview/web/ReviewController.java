package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.ReviewService;
import gdg.six.ddoview.core.domain.review.Review;
import gdg.six.ddoview.core.domain.review.dto.ReplySetRequest;
import gdg.six.ddoview.core.domain.review.dto.ReviewGetResponse;
import gdg.six.ddoview.core.domain.review.dto.ReviewSetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("")
//    public List<Review> getReviewAll() {
//        return reviewService.getAll();
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{reviewId}")
    public ReviewGetResponse getReview(@PathVariable long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ReviewSetRequest.Response createReview(@Valid @RequestBody ReviewSetRequest request) {
        return reviewService.createReview(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/replies")
    public void createReply(@Valid @RequestBody ReplySetRequest request) {
        reviewService.createReply(request);
    }

}
