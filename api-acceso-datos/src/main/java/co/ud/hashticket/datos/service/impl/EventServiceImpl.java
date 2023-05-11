package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.repository.EventRepository;
import co.ud.hashticket.datos.service.EventService;
import co.ud.ud.hashticket.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public EventEntity save(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<EventEntity> findById(Long id) {
        return eventRepository.findById(id);
    }
    @Override
    public Set<EventEntity> getAll(){
        return new HashSet<>(eventRepository.findAll());
    }
    @Transactional
    @Override
    public Boolean updateEventStatus(Long id, EventStatus eventStatus) {
        return eventRepository.updateEventStatus(id, eventStatus) == 1 ? true : false;
    }
}
