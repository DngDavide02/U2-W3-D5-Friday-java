package dangelodavide.U2_W3_D5_Friday.controller;

import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.payload.EventCreateUpdateDTO;
import dangelodavide.U2_W3_D5_Friday.payload.EventDTO;
import dangelodavide.U2_W3_D5_Friday.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvent().stream()
                .map(EventDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(EventDTO.fromEntity(event));
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateUpdateDTO dto) {
        Event event = eventService.createEvent(dto.toEntity());
        return ResponseEntity.ok(EventDTO.fromEntity(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventCreateUpdateDTO dto) {
        Event updated = eventService.updateEvent(id, dto.toEntity());
        return ResponseEntity.ok(EventDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
