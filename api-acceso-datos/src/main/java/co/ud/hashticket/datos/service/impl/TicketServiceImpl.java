package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.*;
import co.ud.hashticket.datos.filtering.JoinEntity;
import co.ud.hashticket.datos.filtering.SearchRequest;
import co.ud.hashticket.datos.filtering.SearchSpecification;
import co.ud.hashticket.datos.mapper.TicketMapper;
import co.ud.hashticket.datos.repository.TicketRepository;
import co.ud.hashticket.datos.service.ConfigEventService;
import co.ud.hashticket.datos.service.TicketService;
import co.ud.hashticket.datos.service.ZoneConfigEventService;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.dto.responses.GenericQuery;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import co.ud.ud.hashticket.exception.BusinessException;
import co.ud.ud.hashticket.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private final ConfigEventService configEventService;
    private final ZoneConfigEventService zoneConfigEventService;
    private final UserLoggerService userLoggerService;
    private final BiFunction<SearchSpecification<TicketEntity>, Pageable, Page<TicketEntity>> findAllTicket = (specification, pageable) -> ticketRepository.findAll(specification, pageable);

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserLoggerService userLoggerService, ConfigEventService configEventService, ZoneConfigEventService zoneConfigEventService) {
        this.ticketRepository = ticketRepository;
        this.userLoggerService = userLoggerService;
        this.configEventService = configEventService;
        this.zoneConfigEventService = zoneConfigEventService;
    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<TicketEntity> getById(TicketPkEntity id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Set<TicketViewDto> getByEventIdAndPresentationId(Long eventId, Long presentationId, Integer records, Integer page) {
        Pageable pageable = PageRequest.of(page, records);
        List<TicketEntity> result = ticketRepository.getByEventIdAndPresentationId(eventId, presentationId, pageable);
        return result.stream().map(TicketMapper.INSTANCE::mapToView)
                .collect(Collectors.toSet());
    }

    @Override
    public Integer countByEventIdAndPresentationId(Long eventId, Long presentationId) {
        return ticketRepository.countByEventIdAndPresentationId(eventId, presentationId);
    }

    @Override
    public Integer updateState(StatusTicket state, Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket, String confirmationNumber, String user) {
        return ticketRepository.updateState(state, eventId, zoneId, categoryId, presentationId, numberTicket, confirmationNumber, user);
    }

    @Override
    @Transactional
    public Optional<TicketEntity> buyTicket(StatusTicket state, Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket) {
        Integer update = this.updateState(state, eventId, zoneId, categoryId, presentationId, numberTicket, this.getAlphaNumericString(40), userLoggerService.getUserLogger());
        if (update != 1) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Se genero actualizacion a mas de un registro");
        }
        Optional<TicketEntity> ticketEntity = this.getById(TicketPkEntity.builder()
                .event(EventEntity.builder()
                        .id(eventId)
                        .build())
                .zone(ZoneEntity.builder()
                        .id(zoneId)
                        .build())
                .category(CategoryEntity.builder()
                        .id(categoryId)
                        .build())
                .presentation(PresentationEntity.builder()
                        .id(presentationId)
                        .build())
                .numberTicket(numberTicket)
                .build());
        if (!ticketEntity.isPresent()) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "No se encontro tickete comprado");
        }
        Optional<Long> idConfigEvent = configEventService.recordSale(eventId, presentationId);
        if (!idConfigEvent.isPresent()) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Imposible actualizar el consolidado en config_event_service");
        }
        boolean valida = zoneConfigEventService.recordSale(zoneId, idConfigEvent.get());
        if (!valida) {
            throw new BusinessException(1L, TYPE_EXCEPTION.ERROR, "Imposible actualizar el consolidado por zona");
        }
        return ticketEntity;
    }

    @Override
    public Set<TicketEntity> getByEmailAndEventAndPresentation(String email, Long eventId, Long presentationId) {
        return ticketRepository.getByEmailAndEventAndPresentation(email, eventId, presentationId);
    }

    @Override
    @Transactional
    public GenericQuery<TicketViewDto> searchTickets(SearchRequest request) {
        BiFunction<SearchSpecification<TicketEntity>, Pageable, GenericQuery<TicketViewDto>> find = findAllTicket.andThen(this::mapper)
                .andThen(item -> filterByJoins(item, request.getJoins()));
        return find.apply(new SearchSpecification<>(request), SearchSpecification.getPageable(request.getPage(), request.getSize()));
    }

    private GenericQuery<TicketViewDto> filterByJoins(GenericQuery<TicketViewDto> data, List<JoinEntity> joins) {
        Set<TicketViewDto> temp = data.getResults();
        for (JoinEntity join : joins) {
            temp = temp.stream().filter(ticket -> {
                if ("event".equalsIgnoreCase(join.getEntity()) && ticket.getEventId().equals(join.getFieldType().parse(join.getValue()))) {
                    return true;
                }
                if ("presentation".equalsIgnoreCase(join.getEntity()) && ticket.getPresentationId().equals(join.getFieldType().parse(join.getValue()))) {
                    return true;

                }
                return false;
            }).collect(Collectors.toSet());
        }
        data.setResults(temp);
        return data;
    }

    private GenericQuery<TicketViewDto> mapper(Page<TicketEntity> ticketEntities) {
        return GenericQuery.<TicketViewDto>builder()
                .results(TicketMapper.INSTANCE.mapToView(new HashSet<>(ticketEntities.getContent())))
                .totalRecords((int) ticketEntities.getTotalElements())
                .page(ticketEntities.getPageable().getPageNumber())
                .records(ticketEntities.getContent().size())
                .build();
    }


    private String getAlphaNumericString(int n) {
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        AlphaNumericString += "0123456789";
        AlphaNumericString += "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}