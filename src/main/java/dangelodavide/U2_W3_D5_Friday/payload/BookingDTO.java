package dangelodavide.U2_W3_D5_Friday.payload;

public record BookingDTO(
        Long id,
        Long userId,
        Long eventId,
        String eventTitle
) {}
