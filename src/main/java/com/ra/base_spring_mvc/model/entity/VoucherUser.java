package com.ra.base_spring_mvc.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher_user")
public class VoucherUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "voucher_id",referencedColumnName = "id")
    private Voucher voucher ;

    private boolean status ;
}
