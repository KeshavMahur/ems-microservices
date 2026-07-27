package com.keshav.ems.notificationservice.serviceImpl;

import com.keshav.ems.notificationservice.entity.Notification;
import com.keshav.ems.notificationservice.event.dto.common.EmployeeCreatedEvent;
import com.keshav.ems.notificationservice.repository.NotificationRepository;
import com.keshav.ems.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    @Override
    public void save(EmployeeCreatedEvent event){

        Notification notification = Notification.builder()

                .employeeId(event.getEmployeeId())

                .employeeName(event.getEmployeeName())

                .address(event.getAddress())

                .message("Employee Created Successfully")

                .createdAt(LocalDateTime.now())

                .build();

        notificationRepository.save(notification);

    }
}
