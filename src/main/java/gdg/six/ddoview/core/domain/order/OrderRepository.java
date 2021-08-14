package gdg.six.ddoview.core.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

//    List<Order> findAllByCompanyId(long companyId);
//
//    Optional<Order> findByCompanyIdAndMemberIdAndTitle(long companyId, long memberId, String companyTitle);
}
