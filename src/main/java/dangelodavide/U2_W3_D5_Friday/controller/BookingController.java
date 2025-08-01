package dangelodavide.U2_W3_D5_Friday.controller;

import dangelodavide.U2_W3_D5_Friday.entities.Booking;
import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.payload.BookingRequest;
import dangelodavide.U2_W3_D5_Friday.payload.BookingDTO;
import dangelodavide.U2_W3_D5_Friday.service.BookingService;
import dangelodavide.U2_W3_D5_Friday.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final EventService eventService;

    public BookingController(BookingService bookingService, EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingRequest request, @AuthenticationPrincipal User user) {
        Event event = eventService.getEventById(request.eventId());
        Booking booking = bookingService.createBooking(user, event);
        BookingDTO response = new BookingDTO(booking.getId(), user.getId(), event.getId(), event.getTitle());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getMyBookings(@AuthenticationPrincipal User user) {
        List<BookingDTO> responseList = bookingService.getByUser(user).stream()
                .map(b -> new BookingDTO(b.getId(), user.getId(), b.getEvent().getId(), b.getEvent().getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId, @AuthenticationPrincipal User user) {
        Booking booking = bookingService.getById(bookingId);
        if (!booking.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        bookingService.deleteBooking(booking);
        return ResponseEntity.noContent().build();
    }
}
