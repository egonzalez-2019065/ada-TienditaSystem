package com.example.bookingSystem.controller.booking;

import com.example.bookingSystem.models.Booking;
import com.example.bookingSystem.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/booking/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(@Autowired BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createUser() {
        URI createBookinUri = URI.create("");
        return ResponseEntity.created(createBookinUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBooking() {
        return ResponseEntity.ok().build();
    }
}
