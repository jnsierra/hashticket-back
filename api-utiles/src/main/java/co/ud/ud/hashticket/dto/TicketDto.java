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
    private String confirmationNumber;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDto ticketDto = (TicketDto) o;

        if (!eventId.equals(ticketDto.eventId)) return false;
        if (!zoneId.equals(ticketDto.zoneId)) return false;
        if (!categoryId.equals(ticketDto.categoryId)) return false;
        if (!presentationId.equals(ticketDto.presentationId)) return false;
        return numberTicket.equals(ticketDto.numberTicket);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + zoneId.hashCode();
        result = 31 * result + categoryId.hashCode();
        result = 31 * result + presentationId.hashCode();
        result = 31 * result + numberTicket.hashCode();
        return result;
    }
}
