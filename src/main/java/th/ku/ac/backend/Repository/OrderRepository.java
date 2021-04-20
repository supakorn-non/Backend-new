package th.ku.ac.backend.Repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ku.ac.backend.Model.OrderProduct;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct,Integer> {
    Collection<OrderProduct> findAllByCid(int cid);
}
