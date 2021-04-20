package th.ku.ac.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cid;
    private String address;
    private String tel;
    private String typeShrimp;
    private String sizeShrimp;
    private int weightShrimp;
    private double price;

    @CreationTimestamp
    private Date date;

    private LocalDateTime time;



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id ;
//
//    @CreationTimestamp
//    private Date createAt;

}
