package com.example.carefully.domain.booking.repository;

import com.example.carefully.domain.booking.entity.Booking;
import com.example.carefully.domain.user.entity.BusinessType;
import com.example.carefully.domain.user.entity.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByIdAndUser(Long bookingId, User user);

    Slice<Booking> findAllByUser(User user);

    Slice<Booking> findAllByAdmin(User admin);

    Slice<Booking> findAllByBusinessType(BusinessType businessType);

    boolean existsBookingByIdAndOperationId(Long bookingId, Long operationId);
}