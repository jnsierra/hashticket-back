package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.TicketPkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketPkMapper {
    TicketPkMapper INSTANCE = Mappers.getMapper(TicketPkMapper.class);
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "presentationId", target = "presentation.id")
    TicketPkEntity map(Long eventId, Long zoneId, Long categoryId, Long presentationId);
}
