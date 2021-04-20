package th.ku.ac.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ku.ac.backend.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByName(String name);
    Customer findByEmail(String email);
    Customer findByTel(String tel);
}
