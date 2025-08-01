package dangelodavide.U2_W3_D5_Friday.repositoy;

import dangelodavide.U2_W3_D5_Friday.entities.Event;
import dangelodavide.U2_W3_D5_Friday.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository <Event, Long> {
    List<Event> findByOrganizer (User organizer);
}
