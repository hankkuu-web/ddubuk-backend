package gdg.six.ddoview.core.domain.goods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

//    List<Goods> findByNameContains(String companyName);
}
