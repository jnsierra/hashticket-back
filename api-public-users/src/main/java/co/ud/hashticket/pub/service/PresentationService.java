package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.PresentationEntity;

import java.util.Set;

public interface PresentationService {
    Set<PresentationEntity> findByEvent(Long eventId);
}