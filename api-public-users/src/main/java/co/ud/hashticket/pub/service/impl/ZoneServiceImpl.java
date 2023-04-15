package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.ZoneEntity;
import co.ud.hashticket.datos.repository.ZoneRepository;
import co.ud.hashticket.pub.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ZoneServiceImpl implements ZoneService {
    private final ZoneRepository zoneRepository;
    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    @Override
    public Set<ZoneEntity> getByCategory(Long idCategory) {
        return zoneRepository.findByCategory(idCategory);
    }
}
