package com.ra.base_spring_mvc.model.dao.productdetail.size;

import com.ra.base_spring_mvc.model.entity.Color;

import com.ra.base_spring_mvc.model.entity.ProductDetail;

import com.ra.base_spring_mvc.model.entity.Size;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SizeDAOImpl implements SizeDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Size> findAll() {
        Session session= sessionFactory.openSession();
        List<Size> sizes=new ArrayList<>();
        try {
            sizes=session.createQuery("from Size ", Size.class)
                    .list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return sizes;
    }


    @Override
    public Size findById(int id) {
        Session session= sessionFactory.openSession();
        Size size=new Size();
        try {
            size=session.get(Size.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return size;
    }

}
