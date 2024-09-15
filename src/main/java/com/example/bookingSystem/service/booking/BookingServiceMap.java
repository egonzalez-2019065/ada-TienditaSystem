package com.example.bookingSystem.service.booking;

import com.example.bookingSystem.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceMap implements BookingService {

    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking updateBooking(int id, Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public Booking deleteBooking(int id) {
        return null;
    }
}
