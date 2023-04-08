package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.EventEntity;
import co.ud.hashticket.datos.repository.EventRepository;
import co.ud.hashticket.pub.service.EventService;
import co.ud.ud.hashticket.enumeration.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Set<EventEntity> getAllActive() {
        return eventRepository.findByEventStatus(EventStatus.CREATED);
    }
    @Override
    public Optional<EventEntity> getById(Long id) {
        return eventRepository.findById(id);
    }
    @Override
    public Optional<EventEntity> getByIdAndPresentationId(Long id, Long idPresentation) {
        return eventRepository.findByIdAndPresentation(id, idPresentation);
    }

}
