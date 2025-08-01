package dangelodavide.U2_W3_D5_Friday.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> createdEvents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;

    public User(String username, String email, String password, Role role, Set<Event> createdEvents, Set<Booking> bookings) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdEvents = createdEvents;
        this.bookings = bookings;
    }
}
