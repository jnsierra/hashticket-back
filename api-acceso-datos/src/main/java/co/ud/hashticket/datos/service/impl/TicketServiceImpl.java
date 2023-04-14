package co.ud.hashticket.datos.service.impl;

import co.ud.hashticket.datos.entity.*;
import co.ud.hashticket.datos.mapper.TicketMapper;
import co.ud.hashticket.datos.repository.TicketRepository;
import co.ud.hashticket.datos.service.TicketService;
import co.ud.hashticket.datos.service.UserService;
import co.ud.hashticket.security.service.UserLoggerService;
import co.ud.ud.hashticket.dto.TicketViewDto;
import co.ud.ud.hashticket.enumeration.StatusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserLoggerService userLoggerService;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserLoggerService userLoggerService) {
        this.ticketRepository = ticketRepository;
        this.userLoggerService = userLoggerService;
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
    public Optional<TicketEntity> buyTicket(StatusTicket state, Long eventId, Long zoneId, Long categoryId, Long presentationId, Long numberTicket)throws Exception{
        Integer update = this.updateState(state, eventId, zoneId, categoryId, presentationId, numberTicket, this.getAlphaNumericString(40), userLoggerService.getUserLogger());
        if(update != 1 ){
            throw new Exception("Se genero actualizacion a mas de un registro");
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
        return ticketEntity;
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
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}