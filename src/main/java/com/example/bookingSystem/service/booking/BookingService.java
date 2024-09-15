package com.example.bookingSystem.service.booking;

import com.example.bookingSystem.models.Booking;

import java.util.List;

public interface BookingService {

    // Guardar a una reserva
    Booking saveBooking(Booking booking);

    // Actualizar a un usuario
    Booking updateBooking(int id, Booking booking);

    // Devolver a los usuarios creados
    List<Booking> getAllBookings();

    // Eliminar a un usuario
    Booking deleteBooking(int id);
}
