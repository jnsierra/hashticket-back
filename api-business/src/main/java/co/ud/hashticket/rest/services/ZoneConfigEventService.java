package co.ud.hashticket.rest.services;

import co.ud.ud.hashticket.dto.ZoneConfigEventDto;

import java.util.Set;

public interface ZoneConfigEventService {

    Set<ZoneConfigEventDto>  getByIdEvent(Long idEvent);
    Set<ZoneConfigEventDto>  getByIdEventAndPresentation(Long idEvent, Long idPresentation);
    ZoneConfigEventDto save(ZoneConfigEventDto zoneConfigEventDto);
    Boolean updateCreateTickets(Long id);
}
