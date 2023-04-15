package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.TicketEntity;

import java.util.Set;

public interface TicketService {
    Set<TicketEntity> getByEventAndPresentationAndZoneAndCategory(Long eventId, Long presentationId, Long zoneId, Long categoryId);
}
