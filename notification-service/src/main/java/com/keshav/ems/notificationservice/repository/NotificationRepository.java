package com.keshav.ems.notificationservice.repository;
import com.keshav.ems.notificationservice.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    Notification save(Notification notification);
}
