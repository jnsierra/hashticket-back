package co.ud.hashticket.pub.service;

import co.ud.hashticket.datos.entity.EventEntity;

import java.util.Set;

public interface EventService {
    Set<EventEntity> getAllActive();
}
