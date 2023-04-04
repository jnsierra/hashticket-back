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
public class TicketViewDto {
    private Long eventId;
    private Long zoneId;
    private String zoneName;
    private Long categoryId;
    private String categoryName;
    private Long presentationId;
    private String presentationName;
    private Long numberTicket;
    private StatusTicket state;
}
