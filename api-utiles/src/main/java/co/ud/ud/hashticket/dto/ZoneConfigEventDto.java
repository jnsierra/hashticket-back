package co.ud.ud.hashticket.dto;

import lombok.Data;

@Data
public class ZoneConfigEventDto {
    private Long id;
    private Long zoneId;
    private Long configEventId;
    private Long numberOfTickets;
}