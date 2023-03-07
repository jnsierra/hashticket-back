package co.ud.hashticket.rest.services.impl;

import co.ud.hashticket.client.ZoneClient;
import co.ud.hashticket.rest.services.ZoneService;
import co.ud.ud.hashticket.dto.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {
    private final ZoneClient zoneClient;
    @Autowired
    public ZoneServiceImpl(ZoneClient zoneClient) {
        this.zoneClient = zoneClient;
    }
    @Override
    public ZoneDto getById(Long idZone) {
        return zoneClient.getById(idZone);
    }
}
