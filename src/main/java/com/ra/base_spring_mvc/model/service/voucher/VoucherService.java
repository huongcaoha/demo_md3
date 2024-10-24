package com.ra.base_spring_mvc.model.service.voucher;

import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.Voucher;
import com.ra.base_spring_mvc.model.entity.dto.BannerDto;
import com.ra.base_spring_mvc.model.entity.dto.VoucherDto;
import com.ra.base_spring_mvc.model.entity.dto.VoucherDtoDisplay;

import java.util.Date;
import java.util.List;

public interface VoucherService {
    List<Voucher> getListVoucher();
    boolean addVoucher(Voucher voucher);
    boolean updateVoucher(Voucher voucher);
    boolean deleteVoucher(Voucher voucher);
    Voucher findById(int id);
    List<Voucher> getListPagination(int page , int size);
    Voucher converseVoucherDto(VoucherDto voucherDto);
    VoucherDto converseVoucher(Voucher voucher);
    boolean CheckCodeExist(String code);
    boolean checkValidStartDate(Date start_date);
    boolean checkValidEndDate(Date start_date,Date end_date);
    List<VoucherDtoDisplay> converseDtoToVoucher(List<Voucher> vouchers, int user_id);
}
