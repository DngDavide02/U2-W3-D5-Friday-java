package dangelodavide.U2_W3_D5_Friday.service;

import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.exception.NotFoundException;
import dangelodavide.U2_W3_D5_Friday.repositoy.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvent(){
       return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con ID " + id + " non trovato"));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = getEventById(id);

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescrcription(updatedEvent.getDescrcription());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setAvailaleSeat(updatedEvent.getAvailaleSeat());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

}
