package gdg.six.ddoview.core.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByCompanyId(long companyId);

    Optional<Review> findByCompanyIdAndMemberIdAndTitle(long companyId, long memberId, String companyTitle);
}
