package co.ud.ud.hashticket.dto;

import co.ud.ud.hashticket.enumeration.StatusTicket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long eventId;
    private Long zoneId;
    private Long categoryId;
    private Long presentationId;
    private Long numberTicket;
    private StatusTicket state;
}
