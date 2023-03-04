package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.repository.EventRepository;
import co.ud.hashticket.datos.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
