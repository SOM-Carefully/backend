package com.example.carefully.domain.booking.entity;

import com.example.carefully.domain.booking.dto.BookingDto;
import com.example.carefully.domain.user.entity.Education;
import com.example.carefully.domain.user.entity.User;
import com.example.carefully.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.carefully.domain.booking.entity.BookingStatus.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@DiscriminatorValue("Booking")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Booking_Type")
public abstract class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String userContent;

    @Column
    private String adminContent;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private BookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;

    @Column(nullable = false)
    private BusinessType businessType;

    private String userFileUrl;

    private String adminFileUrl;

    public Booking(Long id, String userContent, String adminContent, User user, User admin, BookingStatus bookingStatus, BusinessType businessType, String userFileUrl, String adminFileUrl) {
        this.id = id;
        this.user = user ;
        this.admin = admin;
        this.userContent = userContent;
        this.adminContent = adminContent;
        this.bookingStatus = bookingStatus;
        this.businessType = businessType;
        this.userFileUrl = userFileUrl;
        this.adminFileUrl = adminFileUrl;
    }


    //== 비지니스 메서드 ==//

    public void setAdmin(User currentUser) {
        this.admin = currentUser;
    }

    public void accept(BookingDto.ServiceAcceptRequest serviceAcceptRequest) {
        this.bookingStatus = ACCEPT;
        this.adminContent = serviceAcceptRequest.getContent();
        this.adminFileUrl = serviceAcceptRequest.getAdminFileUrl();
    }

    public void cancel(BookingDto.ServiceRejectRequest serviceRejectRequest) {
        this.bookingStatus = CANCEL;
        this.adminContent = serviceRejectRequest.getContent();
    }

    public void ongoing() {
        this.bookingStatus = ONGOING;
    }

    public void complete() {
        this.bookingStatus = COMPLETE;
    }

    public void setNullAdmin() { this.admin = null; }
}