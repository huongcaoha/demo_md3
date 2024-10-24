package com.ra.base_spring_mvc.model.dao.voucherUser;

import com.ra.base_spring_mvc.model.entity.VoucherUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class VoucherUserDAOImpl implements VoucherUserDAO{
    @Autowired
    private final SessionFactory sessionFactory;

    public VoucherUserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<VoucherUser> getVoucherByUser(int user_id) {
        List<VoucherUser> voucherUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
          voucherUsers =  session.createQuery("from VoucherUser v where v.user.id = :_id", VoucherUser.class)
                    .setParameter("_id",user_id).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return voucherUsers ;
    }

    @Override
    public boolean checkVoucherExist(int user_id, int voucher_id) {
        List<VoucherUser> voucherUsers = getListVoucherByUser(user_id);

        if (voucherUsers == null || voucherUsers.isEmpty()) {
            return false;
        }
        return voucherUsers.stream()
                .map(voucherUser -> voucherUser.getVoucher().getId())
                .anyMatch(id -> id == voucher_id);
    }

    @Override
    public List<VoucherUser> getListVoucherByUser(int user_id) {
        List<VoucherUser> voucherUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           voucherUsers = session.createQuery("from VoucherUser vu where vu.user.id = :_id", VoucherUser.class)
                    .setParameter("_id",user_id).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return voucherUsers;
    }

    @Override
    public boolean addVoucherUser(VoucherUser voucherUser) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(voucherUser);
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateVoucherUser(VoucherUser voucherUser) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(voucherUser);
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }
}
