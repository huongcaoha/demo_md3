package com.ra.base_spring_mvc.model.dao.user;

import com.ra.base_spring_mvc.model.constants.RoleName;
import com.ra.base_spring_mvc.model.entity.Role;
import com.ra.base_spring_mvc.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SessionFactory sessionFactory ;
    @Override
    public List<User> getListUser() {
       List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           users = session.createQuery("from User ",User.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public User findById(int id) {
        try (Session session = sessionFactory.openSession()){
           return session.createQuery("from User u where u.id = :_id",User.class)
                    .setParameter("_id",id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }


    @Override
    public boolean register(User user)
    {
        Set<Role> roles = new HashSet<>();

        roles.add(findByRoleName(RoleName.USER));
        
        user.setStatus(true);
        user.setRoles(roles);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        Transaction tx = null;
        try (Session session = sessionFactory.openSession())
        {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return false;
    }

    private Role findByRoleName(RoleName roleName)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Role r where r.roleName = :_roleName", Role.class)
                    .setParameter("_roleName", roleName)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(User user)
    {
        try (Session session = sessionFactory.openSession())
        {
            User userLogin = session.createQuery("from User u where u.username = :_username", User.class)
                    .setParameter("_username", user.getUsername())
                    .getSingleResult();
            if (userLogin != null)
            {
                // kiem tra password
//                if(BCrypt.checkpw(user.getPassword(), userLogin.getPassword())) {
//                    return userLogin;
//                }
                return userLogin;
            }
            return null;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
