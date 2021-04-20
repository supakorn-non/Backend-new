package th.ku.ac.backend.Service;

import org.springframework.stereotype.Service;
import th.ku.ac.backend.Model.Customer;
import th.ku.ac.backend.Model.OrderProduct;
import th.ku.ac.backend.Repository.OrderRepository;

import java.util.Collection;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderProduct product){
        orderRepository.save(product);
    }
    public Collection<OrderProduct> getAllOrder(){
        return orderRepository.findAll();
    }

    public OrderProduct getOrder(int id){
        return orderRepository.findById(id).get();
    }

}
