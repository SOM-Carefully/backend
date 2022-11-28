package com.example.carefully.domain.booking.serivce.impl;

import com.example.carefully.domain.booking.entity.Booking;
import com.example.carefully.domain.booking.dto.BookingDto;
import com.example.carefully.domain.booking.repository.BookingRepository;
import com.example.carefully.domain.booking.serivce.BookingService;
import com.example.carefully.domain.user.entity.User;
import com.example.carefully.domain.user.repository.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.carefully.global.utils.UserUtils.getCurrentUser;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingImpl implements BookingService {

    private final CommonUserRepository commonUserRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public void request(BookingDto.ReceiveRequest receiveRequest) {
        User user = (User) getCurrentUser(commonUserRepository);
        Booking booking = Booking.request(user, receiveRequest);
        bookingRepository.save(booking);
    }
}