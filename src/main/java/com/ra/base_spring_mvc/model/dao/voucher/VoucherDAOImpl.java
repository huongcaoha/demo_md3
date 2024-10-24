package com.ra.base_spring_mvc.model.dao.voucher;

import com.ra.base_spring_mvc.model.entity.Voucher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VoucherDAOImpl implements VoucherDAO{
    @Autowired
    private SessionFactory sessionFactory ;
    @Override
    public List<Voucher> getListVoucher() {
        List<Voucher> vouchers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           vouchers = session.createQuery("from Voucher ",Voucher.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return vouchers;
    }

    @Override
    public List<Voucher> getListPagination(int page, int size) {
        List<Voucher> vouchers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            vouchers = session.createQuery("from Voucher ",Voucher.class)
                    .setFirstResult((page - 1 ) * size)
                    .setMaxResults(size).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return vouchers;
    }

    @Override
    public boolean addVoucher(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(voucher);
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
    public boolean updateVoucher(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(voucher);
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
    public boolean deleteVoucher(Voucher voucher) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(voucher);
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
    public Voucher findById(int _id) {
        try (Session session = sessionFactory.openSession()){
          return   session.createQuery("from Voucher v where v.id = :_id",Voucher.class)
                    .setParameter("_id",_id)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
