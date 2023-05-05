package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.hashticket.datos.repository.ZoneConfigEventRepository;
import co.ud.hashticket.datos.service.ZoneConfigEventService;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;

@Slf4j
@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private ZoneConfigEventRepository zoneConfigEventRepository;
    private BiPredicate<Long,Long> isZoneConfigEventExists = (zoneId, configEventd) -> zoneConfigEventRepository.getByZoneAndConfigEvent(zoneId,configEventd).isPresent();
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventRepository zoneConfigEventRepository) {
        this.zoneConfigEventRepository = zoneConfigEventRepository;
    }
    @Override
    public ZoneConfigEventEntity save(ZoneConfigEventEntity zoneConfigEvent) {
        if(isZoneConfigEventExists.test(zoneConfigEvent.getZone().getId(), zoneConfigEvent.getConfigEvent().getId())){
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Configuración existente");
        }
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