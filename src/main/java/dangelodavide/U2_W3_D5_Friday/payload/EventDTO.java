package dangelodavide.U2_W3_D5_Friday.payload;

import dangelodavide.U2_W3_D5_Friday.entities.Event;

import java.time.LocalDate;

public record EventDTO(
        Long id,
        String title,
        String descrcription,
        LocalDate date,
        int availaleSeat
) {
    public static EventDTO fromEntity(Event e) {
        return new EventDTO(e.getId(), e.getTitle(), e.getDescrcription(), e.getDate(), e.getAvailaleSeat());
    }
}
