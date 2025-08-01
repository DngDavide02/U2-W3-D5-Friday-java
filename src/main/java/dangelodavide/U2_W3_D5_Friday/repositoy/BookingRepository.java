package dangelodavide.U2_W3_D5_Friday.repositoy;

import dangelodavide.U2_W3_D5_Friday.entities.Booking;
import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser (User user);
    boolean existsByUserAndEvent(User user, Event event);
}
