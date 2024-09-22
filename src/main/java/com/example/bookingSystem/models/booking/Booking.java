package com.example.bookingSystem.models.booking;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    private String customerId;
    private Date bookingDate;
    private String details;

    public Booking() {
    }

    public Booking(BookingDto bookingDto) {
        this.customerId = bookingDto.getCustomerId();
        this.bookingDate = bookingDto.getBookingDate();
        this.details = bookingDto.getDetails();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void update(BookingDto bookingDto) {
        this.customerId = bookingDto.getCustomerId();
        this.bookingDate = bookingDto.getBookingDate();
        this.details = bookingDto.getDetails();
    }
}
