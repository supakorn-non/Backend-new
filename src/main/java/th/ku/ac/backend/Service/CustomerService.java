package th.ku.ac.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import th.ku.ac.backend.Model.Customer;
import th.ku.ac.backend.Repository.CustomerRepository;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Random;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository ;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Collection<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id){
        return customerRepository.findById(id).get();
    }

}