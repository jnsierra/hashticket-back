package co.ud.hashticket.datos.mapper;

import co.ud.hashticket.datos.entity.TicketEntity;
import co.ud.ud.hashticket.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    @Mapping(source = "eventId", target = "ticketPk.event.id")
    @Mapping(source = "zoneId", target = "ticketPk.zone.id")
    @Mapping(source = "categoryId", target = "ticketPk.category.id")
    @Mapping(source = "presentationId", target = "ticketPk.presentation.id")
    @Mapping(source = "numberTicket", target = "ticketPk.numberTicket")
    TicketEntity map(TicketDto ticketDto);
    @Mapping(source = "ticketPk.event.id", target = "eventId")
    @Mapping(source = "ticketPk.zone.id", target = "zoneId")
    @Mapping(source = "ticketPk.category.id", target = "categoryId")
    @Mapping(source = "ticketPk.presentation.id", target = "presentationId")
    @Mapping(source = "ticketPk.numberTicket", target = "numberTicket")
    TicketDto map(TicketEntity ticketEntity);
}
