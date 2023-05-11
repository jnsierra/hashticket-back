package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.ConfigEventEntity;
import co.ud.hashticket.datos.entity.ZoneConfigEventEntity;
import co.ud.hashticket.datos.repository.ZoneConfigEventRepository;
import co.ud.hashticket.datos.service.ConfigEventService;
import co.ud.hashticket.datos.service.ZoneConfigEventService;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

@Slf4j
@Service
public class ZoneConfigEventServiceImpl implements ZoneConfigEventService {
    private ZoneConfigEventRepository zoneConfigEventRepository;
    private ConfigEventService configEventService;
    private BiPredicate<Long, Long> isZoneConfigEventExists = (zoneId, configEventd) -> zoneConfigEventRepository.getByZoneAndConfigEvent(zoneId, configEventd).isPresent();
    private final Function<Long, Optional<ConfigEventEntity>> funcGetConfigEvent = idConfigEvent -> this.configEventService.findById(idConfigEvent);
    private static final BiPredicate<Long, Long> isValidNewZoneConfigEvent = (configEvent, zoneConfigEvent) -> configEvent >= zoneConfigEvent;
    private final Function<Optional<ConfigEventEntity>, Long> funcGetLong = configEventEntity -> (configEventEntity.isPresent()) ? configEventEntity.get().getNumberOfTickets().longValue() : 0L;
    private BiFunction<Set<ZoneConfigEventEntity>, Long, Long> funcSumValues = (actualValues, values) -> actualValues.stream().
            mapToLong(ZoneConfigEventEntity::getNumberOfTickets)
            .sum() + values;
    @Autowired
    public ZoneConfigEventServiceImpl(ZoneConfigEventRepository zoneConfigEventRepository
            , ConfigEventService configEventService) {
        this.zoneConfigEventRepository = zoneConfigEventRepository;
        this.configEventService = configEventService;
    }

    @Override
    public ZoneConfigEventEntity save(ZoneConfigEventEntity zoneConfigEvent) {
        validNumberOfTickets(zoneConfigEvent.getConfigEvent().getId(), zoneConfigEvent.getNumberOfTickets(), zoneConfigEvent);
        return zoneConfigEventRepository.save(zoneConfigEvent);
    }
    public void validNumberOfTickets(Long idConfigEvent, Long ticketsZoneConfigEvent, ZoneConfigEventEntity zoneConfigEvent) {
        Long numberTickets = funcGetConfigEvent.andThen(funcGetLong).apply(idConfigEvent);
        //Valido si el total de tickets del evento es mayor o igual a la configuracion de la zona que va a insertar
        boolean validNumber = isValidNewZoneConfigEvent.test(numberTickets, ticketsZoneConfigEvent);
        if (!validNumber) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "El numero de tickets que desea parametrizar es mayor a los parametrizados en el evento");
        }
        //Valido si la suma de todos los zone config events es menor o igual que el total del evento
        Set<ZoneConfigEventEntity> zonesConfig = zoneConfigEventRepository.getByConfigEventId(idConfigEvent);
        if (!zonesConfig.isEmpty() && funcSumValues.apply(zonesConfig,ticketsZoneConfigEvent) > numberTickets) {
                throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "El numero de tickets que deseas insertar supera a los parametrizados para el evento");
        }
        if (isZoneConfigEventExists.test(zoneConfigEvent.getZone().getId(), zoneConfigEvent.getConfigEvent().getId())) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Configuración existente");
        }
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
        if (!entity.isPresent()) {
            return Boolean.FALSE;
        }
        entity.get().setNumberOfTicketsSold(Objects.isNull(entity.get().getNumberOfTicketsSold()) ? 0L : entity.get().getNumberOfTicketsSold() + 1L);
        return Boolean.TRUE;
    }
    @Override
    @Transactional
    public Boolean updateCreateTickets(Long id) {
        if( zoneConfigEventRepository.updateCreateTickets(id, true) != 1 ){
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Error al actualizar zone config event ");
        }
        return true;
    }
}