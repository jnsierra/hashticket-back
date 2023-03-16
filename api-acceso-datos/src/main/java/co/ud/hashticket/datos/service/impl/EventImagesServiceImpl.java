package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.EventImagesEntity;
import co.ud.hashticket.datos.repository.EventImagesRepository;
import co.ud.hashticket.datos.service.EventImagesService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventImagesServiceImpl implements EventImagesService {
    private final EventImagesRepository eventImagesRepository;
    public EventImagesServiceImpl(EventImagesRepository eventImagesRepository) {
        this.eventImagesRepository = eventImagesRepository;
    }
    @Override
    public EventImagesEntity save(EventImagesEntity eventImage) {
        return eventImagesRepository.save(eventImage);
    }

    @Override
    public Set<EventImagesEntity> findAll() {
        return new HashSet<>(eventImagesRepository.findAll());
    }

    @Override
    public Optional<EventImagesEntity> findById(Long id) {
        return eventImagesRepository.findById(id);
    }
}
