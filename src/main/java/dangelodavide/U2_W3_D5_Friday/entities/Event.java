package dangelodavide.U2_W3_D5_Friday.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;
    private String descrcription;
    private LocalDate date;
    private String location;
    private int availaleSeat;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;

    public Event(String title, String descrcription, LocalDate date, String location, User organizer, int availaleSeat, Set<Booking> bookings) {
        this.title = title;
        this.descrcription = descrcription;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.availaleSeat = availaleSeat;
        this.bookings = bookings;
    }
}
