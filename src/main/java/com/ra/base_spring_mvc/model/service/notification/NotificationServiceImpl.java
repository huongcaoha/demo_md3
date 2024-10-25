package com.ra.base_spring_mvc.model.service.notification;

import com.ra.base_spring_mvc.model.dao.notification.NotificationDAO;
import com.ra.base_spring_mvc.model.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private final NotificationDAO notificationDAO ;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public List<Notification> getNotificationByUser(int user_id) {
        return notificationDAO.getNotificationByUser(user_id);
    }

    @Override
    public boolean addNotification(Notification notification) {
        return notificationDAO.addNotification(notification);
    }

    @Override
    public boolean updateNotification(Notification notification) {
        return notificationDAO.updateNotification(notification);
    }

    @Override
    public boolean deleteNotification(Notification notification) {
        return notificationDAO.deleteNotification(notification);
    }

    @Override
    public Notification findById(int id) {
        return notificationDAO.findById(id);
    }
}
