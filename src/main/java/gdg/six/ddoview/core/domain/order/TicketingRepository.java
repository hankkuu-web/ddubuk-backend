package gdg.six.ddoview.core.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketingRepository extends JpaRepository<Ticketing, Long> {

    List<Ticketing> findByOrderId(long orderId);
}
