package co.ud.hashticket.rest.services;

public interface ConfigEventService {
    Long getNumberOfTicketsEventAndPresentation(Long idEvent, Long idPresentation);
    boolean existsConfigEvent(Long idEvent, Long idPresentation);
}
