package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.hashticket.datos.repository.ZoneRepository;
import co.ud.hashticket.datos.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ZoneServiceImpl implements ZoneService {
    private ZoneRepository zoneRepository;
    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    @Override
    public ZoneEntity save(ZoneEntity zone) {
        return zoneRepository.save(zone);
    }
    @Override
    public Optional<ZoneEntity> getById(Long id) {
        return zoneRepository.findById(id);
    }
    @Override
    public Set<ZoneEntity> getAll() {
        return new HashSet<>(zoneRepository.findAll());
    }
}
