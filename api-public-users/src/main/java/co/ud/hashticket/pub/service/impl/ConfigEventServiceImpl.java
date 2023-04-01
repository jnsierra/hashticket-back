package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.hashticket.datos.repository.ConfigEventRepository;
import co.ud.hashticket.pub.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigEventServiceImpl implements ConfigEventService {
    private final ConfigEventRepository configEventRepository;
    @Autowired
    public ConfigEventServiceImpl(ConfigEventRepository configEventRepository) {
        this.configEventRepository = configEventRepository;
    }
    @Override
    public Optional<ConfigEventEntity> findByEventIdAndPresentationId(Long idEvent, Long idPresentation) {
        return configEventRepository.findByEventIdAndPresentation(idEvent, idPresentation);
    }
}
