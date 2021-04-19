package th.ku.ac.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String name ;

    @Column(unique = true)
    private String email ;

    private String tel ;

    private String address ;

    private String password ;

    private String OTP;

}