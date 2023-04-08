package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.hashticket.datos.repository.ZoneConfigEventRepository;
import co.ud.hashticket.pub.service.ZoneConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private ZoneConfigEventRepository zoneConfigEventRepository;
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventRepository zoneConfigEventRepository) {
        this.zoneConfigEventRepository = zoneConfigEventRepository;
    }
    @Override
    public Set<ZoneConfigEventEntity> getByIdEventAndPresentId(Long idEvent, Long idPresent) {
        return this.zoneConfigEventRepository.getZoneConfigByEventAndPresentation(idEvent, idPresent);
    }
}
