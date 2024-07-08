package com.mockproject.AuctionManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification")
public class NotificationEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long idNotification;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "notificationEntity")
    private Set<UserHasNotificationEntity> userHasNotificationEntities =  new HashSet<>();
}
