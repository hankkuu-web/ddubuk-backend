package gdg.six.ddoview.web;

import gdg.six.ddoview.core.application.CompanyService;
import gdg.six.ddoview.core.domain.company.dto.CompanyResponse;
import gdg.six.ddoview.core.domain.company.dto.CompanySetRequest;
import gdg.six.ddoview.core.domain.company.dto.CompanyReviewResponse;
import gdg.six.ddoview.core.domain.company.dto.CompanyTitleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping("/api/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{companyId}")
    public CompanyResponse getCompany(@PathVariable long companyId) {
        return companyService.get(companyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<CompanyTitleResponse> searchCompany(@RequestParam String companyName) {
        return companyService.search(companyName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<CompanyTitleResponse> getCompanyTemplate() {
        return companyService.getTemplateAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{companyId}/reviews")
    public CompanyReviewResponse getCompanyWithReview(@PathVariable long companyId) {
         return companyService.getCompanyWithReview(companyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("")
    public void setCompany(@Valid @RequestBody CompanySetRequest request) {
        companyService.set(request);
    }


}
