package com.ra.base_spring_mvc.model.service.notification;

import com.ra.base_spring_mvc.model.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationByUser(int user_id);
    boolean addNotification(Notification notification);
    boolean updateNotification(Notification notification);
    boolean deleteNotification(Notification notification);
    Notification findById(int id);
}
