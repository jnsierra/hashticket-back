package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.hashticket.datos.repository.ZoneConfigEventRepository;
import co.ud.hashticket.datos.service.ZoneConfigEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private ZoneConfigEventRepository zoneConfigEventRepository;
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventRepository zoneConfigEventRepository) {
        this.zoneConfigEventRepository = zoneConfigEventRepository;
    }
    @Override
    public ZoneConfigEventEntity save(ZoneConfigEventEntity zoneConfigEvent) {
        return zoneConfigEventRepository.save(zoneConfigEvent);
    }
    @Override
    public Optional<ZoneConfigEventEntity> getById(Long id) {
        return zoneConfigEventRepository.findById(id);
    }

    @Override
    public Set<ZoneConfigEventEntity> getZoneConfigByEvent(Long id) {
        return zoneConfigEventRepository.getZoneConfigByEvent(id);
    }
    @Override
    public Set<ZoneConfigEventEntity> getZoneConfigByEventAndPresentation(Long idEvent, Long idPresentation) {
        return zoneConfigEventRepository.getZoneConfigByEventAndPresentation(idEvent, idPresentation);
    }

    @Override
    public Boolean recordSale(Long zoneId, Long configEventId) {
        Optional<ZoneConfigEventEntity> entity = zoneConfigEventRepository.getByZoneAndConfigEvent(zoneId, configEventId);
        if(!entity.isPresent()){
            return Boolean.FALSE;
        }
        entity.get().setNumberOfTicketsSold(Objects.isNull(entity.get().getNumberOfTicketsSold())? 0L : entity.get().getNumberOfTicketsSold() + 1L);
        return Boolean.TRUE;
    }
}