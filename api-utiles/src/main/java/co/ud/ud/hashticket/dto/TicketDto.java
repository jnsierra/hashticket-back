package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.StatusTicket;
import lombok.Data;

@Data
public class TicketDto {
    private Long eventId;
    private Long zoneId;
    private Long categoryId;
    private Long presentationId;
    private Long numberTicket;
    private StatusTicket state;
}
