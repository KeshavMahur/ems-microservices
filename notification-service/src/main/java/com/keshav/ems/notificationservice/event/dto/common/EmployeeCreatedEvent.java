package com.keshav.ems.notificationservice.event.dto.common;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeCreatedEvent {

    private Long employeeId;

    private String employeeName;

    private String address;

    private String departmentName;

}