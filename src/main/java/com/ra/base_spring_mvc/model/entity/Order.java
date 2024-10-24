package com.ra.base_spring_mvc.model.entity;

import com.ra.base_spring_mvc.model.entity.constant.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String serial_number = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user ;

    private double total_price ;

    private StatusEnum status = StatusEnum.WAITING ;
    private String note ;

    @ManyToOne
    @JoinColumn(name = "voucher_id" , referencedColumnName = "id")
    private Voucher voucher;

    private String receive_name ;
    private String receive_address;
    private String receive_phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at = new Date();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date received_at;

}
