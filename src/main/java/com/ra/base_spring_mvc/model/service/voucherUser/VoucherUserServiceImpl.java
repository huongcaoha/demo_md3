package com.ra.base_spring_mvc.model.service.voucherUser;

import com.ra.base_spring_mvc.model.dao.voucherUser.VoucherUserDAO;
import com.ra.base_spring_mvc.model.entity.VoucherUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoucherUserServiceImpl implements VoucherUserService{
    @Autowired
    private final VoucherUserDAO voucherUserDAO;

    public VoucherUserServiceImpl(VoucherUserDAO voucherUserDAO) {
        this.voucherUserDAO = voucherUserDAO;
    }

    @Override
    public List<VoucherUser> getVoucherByUser(int user_id) {
        return voucherUserDAO.getVoucherByUser(user_id);
    }

    @Override
    public boolean checkVoucherExist(int user_id, int voucher_id) {
        return voucherUserDAO.checkVoucherExist(user_id, voucher_id);
    }

    @Override
    public List<VoucherUser> getListVoucherByUser(int user_id) {
        return voucherUserDAO.getListVoucherByUser(user_id);
    }

    @Override
    public boolean addVoucherUser(VoucherUser voucherUser) {
        return voucherUserDAO.addVoucherUser(voucherUser);
    }

    @Override
    public boolean updateVoucherUser(VoucherUser voucherUser) {
        return voucherUserDAO.updateVoucherUser(voucherUser);
    }
}
