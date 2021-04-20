package th.ku.ac.backend.Controller;

import org.springframework.web.bind.annotation.*;
import th.ku.ac.backend.Model.Customer;
import th.ku.ac.backend.Model.OrderProduct;
import th.ku.ac.backend.Repository.OrderRepository;
import th.ku.ac.backend.Service.OrderService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderRepository orderRepository;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    private void createOrder(@RequestBody OrderProduct product) {
        orderService.createOrder(product);
    }

    @PostMapping("/get/{id}")
    private OrderProduct getOrder(@PathVariable int id){
        return orderService.getOrder(id);
    }

    @GetMapping("/getAllById/{cid}")
    private Collection<OrderProduct> getAllOrder(@PathVariable int cid){
        Collection<OrderProduct> respond = new ArrayList<>();
        for (OrderProduct i :orderService.getAllOrder()){
           if (i.getCid() == cid){
               respond.add(i);
           }
       }
       return respond;
    }


}
