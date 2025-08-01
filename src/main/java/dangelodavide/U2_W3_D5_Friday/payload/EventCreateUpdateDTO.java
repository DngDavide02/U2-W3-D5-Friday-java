package dangelodavide.U2_W3_D5_Friday.payload;

import dangelodavide.U2_W3_D5_Friday.entities.Event;

import java.time.LocalDate;

public record EventCreateUpdateDTO(
        String title,
        String descrcription,
        LocalDate date,
        int availaleSeat
) {
    public Event toEntity() {
        Event e = new Event();
        e.setTitle(title);
        e.setDescrcription(descrcription);
        e.setDate(date);
        e.setAvailaleSeat(availaleSeat);
        return e;
    }
}
