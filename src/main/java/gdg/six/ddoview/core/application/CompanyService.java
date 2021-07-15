package gdg.six.ddoview.core.application;

import gdg.six.ddoview.core.domain.company.Company;
import gdg.six.ddoview.core.domain.company.CompanyRepository;
import gdg.six.ddoview.core.domain.company.QuestionRepository;
import gdg.six.ddoview.core.domain.company.dto.CompanyResponse;
import gdg.six.ddoview.core.domain.company.dto.CompanySetRequest;
import gdg.six.ddoview.core.domain.company.dto.CompanyReviewResponse;
import gdg.six.ddoview.core.domain.company.dto.CompanyTitleResponse;
import gdg.six.ddoview.core.domain.review.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewService reviewService;
    private final QuestionRepository questionRepository;

    public CompanyResponse get(long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();

        return CompanyResponse.builder()
                .company(company)
                .build();
    }

    public List<CompanyTitleResponse> getTemplateAll() {
        List<Company> list = companyRepository.findAll();

        return list.stream()
                .map(c -> CompanyTitleResponse.builder()
                        .company(c)
                        .build())
                .collect(Collectors.toList());
    }

    public List<CompanyTitleResponse> search(String companyName) {
        List<Company> list = companyRepository.findByNameContains(companyName);

        return list.stream()
                .map(c -> CompanyTitleResponse.builder()
                        .company(c)
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void set(CompanySetRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow();

        company.updateTemplate(request);
        //companyRepository.save(company);
        questionRepository.saveAll(company.getQuestions());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CompanySetRequest request) {

        Company company = Company.builder()
                            .name(request.getName())
                            .profilePhotoUrl(request.getProfilePhotoUrl())
                            .build();

        company.updateTemplate(request);
        companyRepository.save(company);
        questionRepository.saveAll(company.getQuestions());
    }

    public CompanyReviewResponse getCompanyWithReview(long companyId) {
        List<Review> reviews = reviewService.getByCompanyId(companyId);
        Company company = companyRepository.findById(companyId).orElseThrow();

        return CompanyReviewResponse.builder()
                .company(company)
                .reviews(reviews)
                .build();
    }
}
