package com.example.bookingSystem.service.booking;

import com.example.bookingSystem.models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookingSystem.repository.booking.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceMap {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceMap(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(String id, Booking booking) {
        if (bookingRepository.existsById(id)) {
            return bookingRepository.save(booking);
        }
        return null;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(String id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
    }
}
