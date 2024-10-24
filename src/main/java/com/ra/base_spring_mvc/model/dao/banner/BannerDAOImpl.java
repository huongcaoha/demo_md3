package com.ra.base_spring_mvc.model.dao.banner;

import com.ra.base_spring_mvc.model.entity.Banner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BannerDAOImpl implements BannerDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Banner> getListBanner() {
        List<Banner> banners = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           banners = session.createQuery("from Banner ",Banner.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return banners ;
    }

    @Override
    public List<Banner> getListPagination(int page, int size) {
        List<Banner> banners = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            banners = session.createQuery("from Banner ",Banner.class).setFirstResult((page - 1) * size).setMaxResults(size).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return banners ;
    }

    @Override
    public boolean addBanner(Banner banner) {
      if(banner.getImage() == null){
          return false ;
      }else {
          Session session = sessionFactory.openSession();
          try {
              session.beginTransaction();
              session.save(banner);
              session.getTransaction().commit();
              return true;
          }catch (Exception e){
              session.getTransaction().rollback();
              e.printStackTrace();
              return false;
          }finally {
              session.close();
          }
      }
    }

    @Override
    public boolean updateBanner(Banner banner) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(banner);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteBanner(Banner banner) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(banner);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Banner findById(int _id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Banner b where b.id = :_id", Banner.class)
                    .setParameter("_id",_id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }


    @Override
    public Banner getBannerByLocation(String location) {
        try (Session session = sessionFactory.openSession()){
           return session.createQuery("from Banner b where b.location = :_location",Banner.class)
                    .setParameter("_location",location).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }

}
