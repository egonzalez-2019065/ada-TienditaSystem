package com.example.bookingSystem.service.booking;

import com.example.bookingSystem.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceMap implements BookingService {

    private final BookingService bookingService;

    @Autowired
    public BookingServiceMap(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        Booking bookingSaved = bookingService.saveBooking(booking);
        return bookingSaved;
    }

    @Override
    public Booking updateBooking(int id, Booking booking) {
        Booking bookingToUpdated = bookingService.getBookingById(id);
        if (bookingToUpdated == null) {
             bookingService.saveBooking(booking);
        }
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return bookings;
    }

    @Override
    public Booking getBookingById(int id) {
        Booking bookingFound = bookingService.getBookingById(id);
        return bookingFound;
    }

    @Override
    public void deleteBooking(int id) {
        Booking bookingToDelete = bookingService.getBookingById(id);
        if (bookingToDelete == null) {
            bookingService.deleteBooking(id);
        }
    }
}
