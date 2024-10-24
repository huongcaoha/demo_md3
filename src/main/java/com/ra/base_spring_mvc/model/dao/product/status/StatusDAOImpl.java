package com.ra.base_spring_mvc.model.dao.product.status;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class StatusDAOImpl implements StatusDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Status> findAll() {
        Session session= sessionFactory.openSession();
        List<Status> statusList=new ArrayList<>();
        try {
            statusList=session.createQuery("from Status ", Status.class)
                    .list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return statusList;
    }
}
