package com.ra.base_spring_mvc.model.dao.voucher;

import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.Voucher;

import java.util.List;

public interface VoucherDAO {

    List<Voucher> getListVoucher();
    List<Voucher> getListPagination(int page , int size);
    boolean addVoucher(Voucher voucher);
    boolean updateVoucher(Voucher voucher);
    boolean deleteVoucher(Voucher voucher);
    Voucher findById(int id);


}
