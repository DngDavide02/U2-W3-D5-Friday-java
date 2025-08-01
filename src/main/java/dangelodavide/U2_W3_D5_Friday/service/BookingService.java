package dangelodavide.U2_W3_D5_Friday.service;

import dangelodavide.U2_W3_D5_Friday.entities.Booking;
import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.exception.BadRequestException;
import dangelodavide.U2_W3_D5_Friday.exception.NotFoundException;
import dangelodavide.U2_W3_D5_Friday.repositoy.BookingRepository;
import dangelodavide.U2_W3_D5_Friday.repositoy.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    public BookingService(BookingRepository bookingRepo, EventRepository eventRepo) {
        this.bookingRepository = bookingRepo;
        this.eventRepository = eventRepo;
    }

    public Booking createBooking(User user, Event event) {
        if (event.getAvailaleSeat() <= 0) {
            throw new BadRequestException("non ci sono posti a sedere disponibili");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);

        event.setAvailaleSeat(event.getAvailaleSeat());
        eventRepository.save(event);

        return bookingRepository.save(booking);
    }

    public List<Booking> getByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public Booking getById (Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("booking non trovato"));
    }

    public void deleteBooking (Booking booking) {
        Event event = booking.getEvent();
        event.setAvailaleSeat(event.getAvailaleSeat() + 1);
        eventRepository.save(event);
        bookingRepository.delete(booking);
    }
}
