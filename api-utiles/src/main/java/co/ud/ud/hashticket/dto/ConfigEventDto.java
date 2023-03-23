package co.ud.ud.hashticket.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConfigEventDto {
    private Long id;
    private Long eventId;
    private LocalTime doorOpening;
    private BigDecimal numberOfTickets;
    private BigDecimal numberOfTicketsSold;
    private LocalDate eventDate;
    private Long presentationId;
}
