package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.hashticket.datos.repository.ConfigEventRepository;
import co.ud.hashticket.datos.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ConfigEventServiceImpl implements ConfigEventService {
    private ConfigEventRepository configEventRepository;
    @Autowired
    public ConfigEventServiceImpl(ConfigEventRepository configEventRepository) {
        this.configEventRepository = configEventRepository;
    }
    @Override
    public ConfigEventEntity save(ConfigEventEntity entity) {
        return configEventRepository.save(entity);
    }
    @Override
    public Optional<ConfigEventEntity> findById(Long id) {
        return configEventRepository.findById(id);
    }
    @Override
    public Set<ConfigEventEntity> getAll() {
        return new HashSet<>(configEventRepository.findAll());
    }
    @Override
    public Set<ConfigEventEntity> findByEventId(Long eventId) {
        return configEventRepository.findByEventId(eventId);
    }

    @Override
    public Optional<Long> recordSale(Long eventId, Long presentationId) {
        Optional<ConfigEventEntity> entity = configEventRepository.getByEventIdAndPresentation(eventId, presentationId);
        if(!entity.isPresent()){
            return Optional.empty();
        }
        entity.get().setNumberOfTicketsSold(entity.get().getNumberOfTicketsSold().add(BigDecimal.ONE));
        return Optional.of(entity.get().getId());
    }
    @Override
    public Optional<ConfigEventEntity> findByEventIdAndPresentationId(Long eventId, Long presentationId) {
        return configEventRepository.getByEventIdAndPresentation(presentationId,eventId);
    }
}
