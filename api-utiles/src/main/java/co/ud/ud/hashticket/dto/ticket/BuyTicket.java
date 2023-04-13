package co.ud.ud.hashticket.dto.ticket;

import lombok.Data;

import java.util.Set;

@Data
public class BuyTicket {
    private Long eventId;
    private Long presentationId;
    private Long zonaId;
    private Long categoryId;
    private Long numberOfTickets;
    private Set<String> numberTickets;
}