package com.ra.base_spring_mvc.model.dao.voucherUser;

import com.ra.base_spring_mvc.model.entity.VoucherUser;

import java.util.List;

public interface VoucherUserDAO {
    List<VoucherUser> getVoucherByUser(int user_id);
    boolean checkVoucherExist(int user_id , int voucher_id);
    List<VoucherUser> getListVoucherByUser(int user_id);
    boolean addVoucherUser(VoucherUser voucherUser);
    boolean updateVoucherUser(VoucherUser voucherUser);

}
