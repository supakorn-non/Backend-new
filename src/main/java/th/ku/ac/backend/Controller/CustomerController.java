package th.ku.ac.backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import th.ku.ac.backend.Model.Customer;
import th.ku.ac.backend.Service.CustomerService;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    private CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    private void createCustomer(@RequestBody Customer customer) {
        customer.setPassword(generateEncryptPassword(customer.getPassword()));
        customerService.createCustomer(customer);
    }

    @PostMapping("/update")
    private void updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }

    @PostMapping("/get/{id}")
    private Customer getCustomer(@PathVariable int id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/getAll")
    private Collection<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @PostMapping("/forget/send/{id}")
    private void customerForgetPassword(@PathVariable int id){
        String OTP = generateOTP();
        if (sendOTPToEmail(customerService.getCustomer(id),OTP)) {
            Customer record = customerService.getCustomer(id);
            record.setOTP(OTP);
            customerService.updateCustomer(record);
        }
    }

    @PostMapping("/forget/receive/{id}")
    private boolean customerSendOTP(@PathVariable int id, @RequestBody String OTP){
        if (customerService.getCustomer(id).getOTP().equals(OTP)) {
            Customer record = customerService.getCustomer(id);
            record.setOTP(null);
            customerService.updateCustomer(record);
            return true;
        }
        return false;
    }

    @PostMapping("/{id}/changePassword")
    private void customerChangePassword(@PathVariable int id, @RequestBody String password){
        Customer record = customerService.getCustomer(id);
        record.setPassword(generateEncryptPassword(password));
        customerService.updateCustomer(record);
    }


    private static String generateEncryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private static String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    private static boolean sendOTPToEmail(Customer customer,String OTP){
        String host = "mail.gmail.com";
        String from = "";
        String password = "";
        String to = customer.getEmail();
        String subject = "";
        Properties properties = new Properties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            message.setSubject(subject);
            message.setText(OTP);
            Transport.send(message);
            return true;
        }catch (MessagingException messagingException){
            System.out.println(messagingException.getMessage());
        }
        return false;
        }




}