package com.example.bookingSystem.controller.booking;

import com.example.bookingSystem.models.booking.Booking;
import com.example.bookingSystem.models.booking.BookingDto;
import com.example.bookingSystem.service.booking.BookingServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/booking/")
public class BookingController {

    private final BookingServiceMap bookingServiceMap;

    @Autowired
    public BookingController(BookingServiceMap bookingServiceMap) {
        this.bookingServiceMap = bookingServiceMap;
    }


    @PostMapping
    public ResponseEntity<Booking> createUser(@RequestBody BookingDto bookingDto) {
        Booking booking = new Booking(bookingDto);
        Booking savedBooking = bookingServiceMap.saveBooking(booking);
        URI createdBookingUri = URI.create("/v1/booking/" + savedBooking.getId());
        return ResponseEntity.created(createdBookingUri).build();
    }

    @GetMapping
    public ResponseEntity<List<Booking>>getAllBookings() {
        List<Booking> bookings = bookingServiceMap.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> findById(@PathVariable String id) {
        Optional <Booking> bookingFound = bookingServiceMap.getBookingById(id);
        if (bookingFound.isPresent()) {
            return ResponseEntity.ok(bookingFound.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody BookingDto bookingDto) {
        Optional <Booking> booking = bookingServiceMap.getBookingById(id);
        if (booking.isPresent()) {
            Booking userToUpdated = booking.get();
            userToUpdated.update(bookingDto);
            bookingServiceMap.saveBooking(userToUpdated);
            return ResponseEntity.ok(userToUpdated);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        Optional <Booking> booking = bookingServiceMap.getBookingById(id);
        if (booking.isPresent()) {
            bookingServiceMap.deleteBooking(id);
            return ResponseEntity.ok().build();
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
