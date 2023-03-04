package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.hashticket.datos.repository.ConfigEventRepository;
import co.ud.hashticket.datos.service.ConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
